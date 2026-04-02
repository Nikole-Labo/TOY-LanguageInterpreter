package Repository;

import Module.ProgramState;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Repository implements InterfaceRepository
{
    private List<ProgramState> repository;
    private String logFilePath;

    public Repository(ProgramState programState, String file)
    {
        this.logFilePath = file;
        try
        {
            PrintWriter tmp = new PrintWriter(file);
            tmp.write("");
            tmp.close();
        }

        catch (FileNotFoundException ignored) {}
        repository =new ArrayList<>();
        repository.add(programState);
    }

    public List<ProgramState> getPrograms(){
        return this.repository;
    }

    public void add(ProgramState state) {
        repository.add(state);
    }

    public ProgramState getCurrentState() {
        return repository.get(0);
    }

    @Override
    public void logProgramStateExecutor(ProgramState programState)
    {
        try
        {
            PrintWriter logfile = new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath, true)));
            logfile.println(programState.toString());
            logfile.println("-----------------------------------------------------------------------------------");
            logfile.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void setProgramList(List<ProgramState> list) {
        repository = list;
    }

    @Override
    public List<ProgramState> getPrgList(){
        return repository;
    }
}