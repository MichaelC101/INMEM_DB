import java.util.HashMap;
import java.util.Map;

public class InMemoryDB {
    private Map<String, Integer> store = new HashMap<>();
    private Map<String, Integer> trans = new HashMap<>();
    private boolean inTransaction = false;

    public Integer get(String key) {
        return store.getOrDefault(key, null);
    }

    public void put(String key, int val) {
        if (!inTransaction) {
            throw new IllegalStateException("Error !!! No transaction in progress");
        }
        trans.put(key, val);
    }

    public void begin_transaction() {
        inTransaction = true;
        trans.clear();
    }

    public void commit() {
        if (!inTransaction) {
            throw new IllegalStateException("No transaction in progress");
        }
        store.putAll(trans);
        inTransaction = false;
    }

    public void rollback() {
        if (!inTransaction) {
            throw new IllegalStateException("No transaction in progress");
        }
        trans.clear();
        inTransaction = false;
    }
}