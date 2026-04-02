package Controller;
import Module.ProgramState;
import Module.Values.ReferenceValue;
import Module.Values.StringValue;
import Module.Statements.InterfaceStatement;
import Module.Values.InterfaceValue;
import Repository.InterfaceRepository;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller
{
    private InterfaceRepository repository;
    private ExecutorService executor;

    public Controller(InterfaceRepository repo)
    {
        this.repository = repo;
    }

    Map<Integer, InterfaceValue> unsafeGarbageCollector(List<Integer> symTableAddr, Map<Integer, InterfaceValue> heap)
    {
        return heap.entrySet().stream()
                .filter(e -> symTableAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    List<Integer> getAddrFromSymTable(Collection<InterfaceValue> symTableValues)
    {
        return symTableValues.stream()
                .filter(v-> v instanceof ReferenceValue)
                .map(v-> {ReferenceValue v1 = (ReferenceValue) v; return v1.getAddress();})
                .collect(Collectors.toList());
    }

    Map<Integer, InterfaceValue> safeGarbageCollector(List<Integer> symTableAddr, Map<Integer, InterfaceValue> heap){
        List<Integer> heapAddr = heap.values().stream()
                .filter(value -> value instanceof ReferenceValue)
                .map(value->{ReferenceValue va = (ReferenceValue) value;
                    return va.getAddress();})
                .collect(Collectors.toList());

        heapAddr.forEach(v -> {if(!symTableAddr.contains(v)) symTableAddr.add(v);});

        return heap.entrySet().stream().filter(e-> symTableAddr.contains(e.getKey())).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    List<ProgramState> removeCompletedPrg(List<ProgramState> inPrgList)
    {
        List<ProgramState> filteredPrgList = inPrgList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
        return filteredPrgList;
    }

    public void oneStep()
    {
        executor = Executors.newFixedThreadPool(2);
        List<ProgramState> prgList = removeCompletedPrg(this.repository.getPrgList());
        if(prgList.size() > 0)
        {
            prgList.forEach(prg -> prg.getHeap().
                    setHeap(safeGarbageCollector(getAddrFromSymTable(prg.getSymbolTable().getContent().values()),
                            prg.getHeap().getHeap())));
            try
            {
                oneStepForAllPrg(prgList);
            }
            catch (InterruptedException e)
            {
                System.out.println(e.getMessage());
            }
        }
        repository.setProgramList(prgList);
        executor.shutdownNow();
    }


    void oneStepForAllPrg(List<ProgramState> prgList) throws InterruptedException
    {
        List<Callable<ProgramState>> callList =
                prgList.stream().map((ProgramState p)->(Callable<ProgramState>)(() -> {return p.oneStep();}))
                        .collect(Collectors.toList());
        List<ProgramState> newPrgList = executor.invokeAll(callList).stream().map(future -> {
            try
            {
                return future.get();
            }
            catch (InterruptedException | ExecutionException e)
            {
                System.out.println(e.getMessage());
                return null;
            }}).filter(p -> p!= null).collect(Collectors.toList());

        prgList.addAll(newPrgList);


        prgList.forEach(prg -> repository.logProgramStateExecutor(prg));
        repository.setProgramList(prgList);
    }

    public void allSteps()
    {
        executor = Executors.newFixedThreadPool(2);
        List<ProgramState>prgList = removeCompletedPrg(repository.getPrgList());
        prgList.forEach(prg -> repository.logProgramStateExecutor(prg));
        System.out.println(prgList + "\n");
        while(!prgList.isEmpty())
        {
            prgList.forEach(prg -> prg.getHeap().
                    setHeap(safeGarbageCollector(getAddrFromSymTable(prg.getSymbolTable().getContent().values()),
                            prg.getHeap().getHeap())));
            System.out.println(prgList + "\n");
            try
            {
                oneStepForAllPrg(prgList);
            }
            catch (InterruptedException e)
            {
                System.out.println(e.getMessage());
                prgList = removeCompletedPrg(repository.getPrgList());
                break;
            }
            prgList = removeCompletedPrg(repository.getPrgList());
        }
        executor.shutdownNow();

        repository.setProgramList(prgList);
    }

    public List<ProgramState> getPrograms(){
        return repository.getPrgList();
    }

    public int getNrOfPrgStates(){
        return repository.getPrgList().size();
    }

    public List<Map.Entry<Integer, String>> getHeapTable(){
        ArrayList<Map.Entry<Integer, String>> a = new ArrayList<>();
        for(Map.Entry<Integer, InterfaceValue> e : new ArrayList<>(repository.getPrgList().get(0).getHeap().getHeap().entrySet()) )
        {
            a.add(Map.entry(e.getKey(), e.getValue().toString()));
        }
        return a;
    }
    public ArrayList<InterfaceStatement> getExeStack(int prgId)
    {
        ProgramState pg = null;
        for( ProgramState prgState : new ArrayList<>(repository.getPrgList()))
        {
            if(prgState.getId() == prgId)
            {
                pg = prgState;break;
            }
        }
        return new ArrayList<>(pg.getExecutionStack().values());

    }

    public ArrayList<Map.Entry<String, String>> getSymTable(int prgId)
    {
        ArrayList<Map.Entry<String, String>> res = new ArrayList<>();
        ProgramState pg = null;
        for( ProgramState prgState : new ArrayList<>(repository.getPrgList()))
        {
            if(prgState.getId() == prgId){
                pg = prgState;break;
            }
        }
        for(Map.Entry<String, InterfaceValue> e : pg.getSymbolTable().getContent().entrySet())
        {
            res.add(Map.entry(e.getKey(), e.getValue().toString()));
        }
        return res;
    }

    public ArrayList<Map.Entry<String, String>> getFileTable()
    {
        ArrayList<Map.Entry<String, String>> a = new ArrayList<>();
        for(Map.Entry<StringValue, BufferedReader> e : new ArrayList<>(repository.getPrgList().get(0).getFileTable().getContent().entrySet()) ){
            a.add(Map.entry(e.getKey().toString(), e.getValue().toString()));
        }
        return a;
    }

    public ArrayList<Integer> getPrgStateIds()
    {
        ArrayList<Integer> ids = new ArrayList<>();
        for (ProgramState pg : this.repository.getPrgList())
        {
            ids.add(pg.getId());
        }
        return ids;
    }
    public List<InterfaceValue> getOut() {
        return new ArrayList<>(repository.getPrgList().get(0).getOut().values());
    }
}
