package Repository;

import Module.ProgramState;

import java.util.List;

public interface InterfaceRepository
{
    void add(ProgramState state);
    ProgramState getCurrentState();
    void logProgramStateExecutor(ProgramState programState);
    void setProgramList(List<ProgramState> list);
    List<ProgramState> getPrgList();
    List<ProgramState> getPrograms();
}
