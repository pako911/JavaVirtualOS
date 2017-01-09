/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semaphore;

/**
 *
 * @author LUCYNA
 */
public class InvalidSemaphoreValueException extends Exception
{
    public InvalidSemaphoreValueException()
    {
        super("Nieprawdiłowa wartośc semafora");
    }
}
