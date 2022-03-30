import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class LocalEleStat {

    private String no, surname,firstName,party,localElectoralArea;
    private Address address;

    public LocalEleStat(String s)
    {
        try {
            Scanner sc = new Scanner(s);

            sc.useDelimiter("\"");

            String[] part1 = sc.next().split(",");
            address = new Address(sc.next());
            String[] part3 = sc.next().split(",");

            no = part1[0];
            surname = encode(part1[1]);
            firstName = encode(part1[2]);

            party = encode(part3[1]);
            localElectoralArea = part3[2];
        }
        catch (Exception e)
        {
            //ignore exceptions
            throw new IllegalArgumentException("doesnt fit");
        }
    }

    private String encode(String s) // converts all the characters to UTF_8
    {
        ByteBuffer buffer = StandardCharsets.UTF_8.encode(s);
        return StandardCharsets.UTF_8.decode(buffer).toString();
    }

    private String pad(String s, int padding)
    {
        String temp = "";
        for(int i = 0; i < padding; i++)
        {
            temp = temp + " ";
        }

        return (s+temp).substring(0, padding);

    }

    public String toCSV()
    {
        return String.format("%s,%s,%s,%s,%s",no, surname, firstName, party, localElectoralArea);
    }
    @Override
    public String toString() {
        return String.format("<tr><td>%s</td><td>%s</td><td>%s</td></tr>", (surname+"," +firstName), party, localElectoralArea);
        //return surname + ", \t" + firstName + "\t(" + party +") \t\t\t" + localElectoralArea;
    }

    public String getNo() {
        return no;
    } // returns the number of the specified member

    public String getSurname() {
        return surname;
    } // returns the surname of the specified member

    public String getFirstName() {
        return firstName;
    } // returns the firstname of the specified member

    public String getParty() {
        return party;
    } // returns the party of the specified member

    public String getLocalElectoralArea() {
        return localElectoralArea;
    } // returns the local electoral area of the specified member

    public Address getAddress() {
        return address;
    } // returns the address of the specified member

    class Address // class to store the address
    {
        String [] lines;

        public Address(String s)
        {
            lines = s.split(","); // splits the address at the commas
        }

        @Override
        public String toString() {
            return  Arrays.toString(lines); // returns the array as a string
        }
    }
}
