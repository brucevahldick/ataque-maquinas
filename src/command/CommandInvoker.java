package command;

/**
 *
 * @author brucevahldick
 */
public class CommandInvoker {
    public void executar(Command command) throws Exception{
        command.executar();
    }
}
