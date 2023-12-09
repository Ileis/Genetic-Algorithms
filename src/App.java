public class App {
    public static void main(String[] args) throws Exception {

        int num = 21;

        AllocationSchedule a1 = new AllocationSchedule(1, num);
        AllocationSchedule a2 = new AllocationSchedule(1, num);

        String b = "111111111111111111111";
        String c = "000000000000000000000";
        // String d = "111110000000000000000";
        // String e = "000000000000000000000";

        a1.setSchedule(b);
        a2.setSchedule(c);

        a1.mutate();
        System.out.println(a1);
        a2.mutate();
        System.out.println(a2);

        // AllocationSchedule a3 = AllocationSchedule.reproduction(a1, a2);

        // System.out.println(b.length());

    }
}