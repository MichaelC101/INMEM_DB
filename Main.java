public class Main {
    public static void main(String[] args) {
        InMemoryDB inmemoryDB = new InMemoryDB();
        System.out.println("Get A (should be null): " + inmemoryDB.get("A"));
        try {
            inmemoryDB.put("A", 5);
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Initial State of A: " + inmemoryDB.get("A"));
        inmemoryDB.begin_transaction();
        inmemoryDB.put("A", 5);
        System.out.println("Get A within transaction (should be null): " + inmemoryDB.get("A"));
        inmemoryDB.put("A", 6);
        try {
            inmemoryDB.commit();
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Get A after commit (should be 6): " + inmemoryDB.get("A"));
        inmemoryDB.begin_transaction();
        inmemoryDB.put("B", 10);
        inmemoryDB.rollback();
        System.out.println("Get B after rollback (should be null): " + inmemoryDB.get("B"));
    }
}