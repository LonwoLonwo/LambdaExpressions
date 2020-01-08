import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

public class Main
{
    //private static String staffFile = "G:\\Java Projects\\LambdaExpressions\\LambdaExpressions\\data\\staff.txt"; //мой путь
    private static String staffFile = "data/staff.txt";
    private static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args)
    {
        ArrayList<Employee> staff = loadStaffFromFile();

        /*Collections.sort(staff, (o1, o2) -> {
            if(o1.getSalary() < o2.getSalary()){
                return -1;
            }
            else if(o1.getSalary() > o2.getSalary()){
                return 1;
            }
            else{
                return o1.getName().compareTo(o2.getName());
            }
        });
        for(Employee employee : staff){
            System.out.println(employee);
        }*/

        System.out.println("Макимальная зарплата сотрудника, пришедшего в 2017 году, у:");
        staff.stream()
                .filter(e -> e.getWorkStart().toString().endsWith("2017"))
                .max(Comparator.comparing(Employee::getSalary))
                .ifPresent(System.out::println);
    }

    private static ArrayList<Employee> loadStaffFromFile()
    {
        ArrayList<Employee> staff = new ArrayList<>();
        try
        {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            for(String line : lines)
            {
                String[] fragments = line.split("\t");
                if(fragments.length != 3) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                staff.add(new Employee(
                    fragments[0],
                    Integer.parseInt(fragments[1]),
                    (new SimpleDateFormat(dateFormat)).parse(fragments[2])
                ));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return staff;
    }
}