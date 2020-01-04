import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

public class Main
{
    //private static String staffFile = "G:\\Java Projects\\LambdaExpressions\\LambdaExpressions\\data\\staff.txt"; *мой путь
    private static String staffFile = "data/staff.txt";
    private static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args)
    {
        ArrayList<Employee> staff = loadStaffFromFile();

        //вариант 0,5 - тоже рабочий
        /*Collections.sort(staff, (o1, o2) ->
            o1.getName().compareTo(o2.getName())
        );
        Collections.sort(staff, (o1, o2) ->
                o1.getSalary().compareTo(o2.getSalary())
        );*/

        Collections.sort(staff, (o1, o2) -> {
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
        }
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