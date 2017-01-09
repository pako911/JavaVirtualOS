package procesor;

import java.util.ArrayList;

public class Scheduler {
    
    Scheduler(){};
    
    public ArrayList<Proces> ready_process_list=new ArrayList<Proces> ();
    int last_time;
    int theta_first=10;
    int[] theta;
    int shortest;
    Proces Running;
    Proces temp;
    double alpha;
    
    void InitScheduler(){
        Running=null;
        last_time=0;
        theta_first=10;
        theta=new int[100];
        alpha=0.5;
    }
    
    public void add_proces(Proces proces)
    {
        ready_process_list.add(proces);
    }
    
    void SJF(){
        if(Running!=null)
        {
            last_time=Running.timer;
        }
        
        if(Running!=null && Running.state_process == Proces.State.WAITING )
        {
           return;
        }
        else{
            if(ready_process_list!=null){
                                
                theta[0]=shortest=(int)((alpha*last_time)+(1-alpha)*theta_first);

                temp=ready_process_list.get(ready_process_list.size()-1);
                
                for(int index=1;index<ready_process_list.size();index++)
                {
                    theta[index]=(int)((alpha*last_time)+(1-alpha)*theta[index-1]);
                    
                    if(theta[index]<shortest){
                   
                        temp=ready_process_list.get(index);
                        shortest=theta[index];
                    }
                }
                Running=temp;
                //Running.change_state(Running.PID, Proces.State.ACTIVE);
            }
            else
            {
                Running=null;
            }
        }
        
    }
    
    void SRT(){
        if(Running!=null)
        {
            last_time=Running.timer;
        }
        
        if(Running!=null && Running.state_process == Proces.State.WAITING )
        {
           return;
        }
        else{
            if(ready_process_list!=null){
                                
                theta[0]=shortest=(int)((alpha*last_time)+(1-alpha)*theta_first);
           
            
                temp=ready_process_list.get(ready_process_list.size()-1);
                
                for(int index=1;index<ready_process_list.size();index++)
                {
                    theta[index]=(int)((alpha*last_time)+(1-alpha)*theta[index-1]);
                    
                    if(theta[index]<shortest){
                        shortest=theta[index];
                        
                        Running=ready_process_list.get(index);
                        
                    }
                }
                //Running.change_state(Running.PID, Proces.State.ACTIVE);
            }
            else
            {
                Running=null;
            }
        }
        
    }
    
}