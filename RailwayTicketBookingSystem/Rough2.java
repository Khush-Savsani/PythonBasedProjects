import java.util.*;
import java.sql.*;

class UserLogin
{
    Scanner sc = new Scanner(System.in);
    
    boolean loggedIn = false;
    static String username;

    String password;

    String confirmPassword;
    int login_id;

    void userLogin(Connection con) throws Exception
    {
        ArrayList<String> UN = new ArrayList<>();
        String sql = "SELECT username FROM rbs";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next())
        {
            UN.add(rs.getString("username"));
        }

        boolean isUnique = false;

        System.out.println("Log-In to Create Your Account:\n");

        System.out.println("Enter Your Username (must be greater than or equal to 7 characters): ");

        boolean loginstatus = true;
        boolean passwordstatus = true, confirmpasswordstatus = true;

        while(loginstatus)
        {
            // System.out.print("Enter a new username: ");
            // username = sc.next();
            while(!isUnique)
            {
                System.out.print("Enter a new username: ");
                username = sc.next();

                if(UN.contains(username))
                {
                    System.out.println("Username already exists. Please choose a different username.\n");
                }
                else
                {
                    isUnique = true;
                }
            }
            if(username.length()>=7)
            {
                while(passwordstatus)
                {
                    System.out.print("\nEnter your Password (must be greater or equal to than 8 characters): ");
                    password = sc.next();
                    if(password.length()>=8)
                    {
                        while(confirmpasswordstatus)
                        {
                            System.out.println("\nConfirm Your Password.");
                            System.out.print("Enter Confirm Password: ");
                            confirmPassword = sc.next();
                            if(confirmPassword.equals(password))
                            {
                                System.out.println("Password added successfully.");
                                loggedIn = true;
                                confirmpasswordstatus = false;
                            }
                            else
                            {
                                System.out.println("\nConfirmed Password is Invalid. Please enter Confirm Password carefully.");
                            }
                        }
                        passwordstatus = false;
                    }
                    else
                    {
                        System.out.println("Password is Invalid. Please enter Password carefully.\n");
                    }
                }
                loginstatus = false;
            }
            else
            {
                System.out.println("\nUsername is Incorrect. Please enter valid Username.\n");
                isUnique = false;
            }
        }
        String sql1 = "INSERT INTO rbs(Username,Password) VALUES(?,?)";
        PreparedStatement pst1 = con.prepareStatement(sql1);
        pst1.setString(1, username);
        pst1.setString(2, password);
        int r = pst1.executeUpdate();
        if(r>0)
        {
            System.out.println("\nLog-In Successful.");
        }
        else
        {
            System.out.println("\nLog-In Failed.");
        }
        String sql2 = "SELECT LoginId FROM rbs WHERE Username = ?";
        PreparedStatement pst2 = con.prepareStatement(sql2);
        pst2.setString(1, username);
        ResultSet rs1 = pst2.executeQuery();
        while(rs1.next())
        {
            login_id = rs1.getInt(1);
            System.out.println("Log-In ID: "+login_id);
        }
    }
    public boolean isLoggedIn()
    {
        return loggedIn;
    }
}
class Passenger
{
    String fullName, gender, mobileNo;
    int age;
    String formattedDate;

    public Passenger(String fullName, String gender, String mobileNo, int age, String formattedDate)
    {
        this.fullName = fullName;
        this.gender = gender;
        this.mobileNo = mobileNo;
        this.age = age;
        this.formattedDate = formattedDate;
    }

    public String toString()
    {
        return "Passenger full Name : " + fullName + "\n" + "Gender : " + gender + "\n" + "Age : " + age + "\n" + "Mobile No. : " + mobileNo +"\n" + "Date: " + formattedDate;
    }
}

class TicketBooking extends ViewBooking
{
    ArrayList<Passenger> details = new ArrayList<>();

    HashMap<Integer,Integer> date = new HashMap<>();

    HashSet<String> bookedSeats = new HashSet<>();

    UserLogin UL = new UserLogin();
    
    String from, SOURCE, to, DESTINATION, time;
    double price, total_price, amount;
    int day = 0, month = 0, year = 0, l = 0, ticket_status;
    boolean ticket_booked = false;
    String formattedDate;

    int trainId;
    String trainName, departureTime, arrivalTime;

    String firstName, middleName, lastName, fullName, gender, mobileNo;
    int age;
    String r1 = "AHMEDABAD", r2 = "JUNAGADH", r3 = "DWARKA", r4 = "RAJKOT", r5 = "BHAVNAGAR", r6 = "JAMNAGAR", r7 = "NAVSARI", r8 = "VERAVAL", r9 = "BHUJ", r10 = "SURAT";
    Scanner sc = new Scanner(System.in);

    void bookTicket(Connection con) throws Exception
    {
        date.put(1,31); // January
        date.put(2,28); // February (check for Leap Year also)
        date.put(3,31); // March
        date.put(4,30); // April
        date.put(5,31); // May
        date.put(6,30); // June
        date.put(7,31); // July
        date.put(8,31); // August
        date.put(9,30); // September
        date.put(10,31); // October
        date.put(11,30); // November
        date.put(12,31); //December

        boolean check_date;
        while(true)
        {
            System.out.println("\nHere Only 10 Routes are available.");
            System.out.println("1. " + r1 + "  " + "2. " + r2 + "  " + "3. " + r3 + "  " + "4. " + r4 + "  " + "5. " + r5 + "  " + "6. " + r6 + "  " + "7. " + r7 + "  " + "8. " + r8 + "  " + "9. " + r9 + "  " + "10. " + r10);
            System.out.println();

            boolean check_routes = true;
            while(check_routes)
            {
                System.out.print("FROM (Write Station Name): ");
                from = sc.next();
                SOURCE = from.toUpperCase();
                SOURCE = SOURCE.replaceAll("\\s+", ""); //It is used to eliminate extra spaces if a user has accidentally typed it.
                if(SOURCE.equals(r1) || SOURCE.equals(r2) || SOURCE.equals(r3) || SOURCE.equals(r4) || SOURCE.equals(r5) || SOURCE.equals(r6) || SOURCE.equals(r7) || SOURCE.equals(r8) || SOURCE.equals(r9) || SOURCE.equals(r10))
                {
                    check_routes = false;
                }
                else
                {
                    System.out.println("Entered Route is invalid. Please enter valid Route.");
                }
            }
            check_routes = true;
            while(check_routes)
            {
                System.out.print("TO (Write Station Name): ");
                to = sc.next();
                DESTINATION = to.toUpperCase();
                DESTINATION = DESTINATION.replaceAll("\\s+", ""); //It is used to eliminate extra spaces if a user has accidentally typed it.
                if(DESTINATION.equals(r1) || DESTINATION.equals(r2) || DESTINATION.equals(r3) || DESTINATION.equals(r4) || DESTINATION.equals(r5) || DESTINATION.equals(r6) || DESTINATION.equals(r7) || DESTINATION.equals(r8) || DESTINATION.equals(r9) || DESTINATION.equals(r10))
                {
                    check_routes = false;
                }
                else
                {
                    System.out.println("Entered Route is invalid. Please enter vailed Route.");
                }
                sc.nextLine();
            }
            if(SOURCE.equals(DESTINATION))
            {
                System.out.println("\nYou have entered invalid Routes. Please enter Routes carefully.\n");
                check_routes = true;
            }
            else
            {
                String sql1 = "SELECT * FROM routes WHERE SOURCE = ? AND DESTINATION = ?";
                PreparedStatement pst1 = con.prepareStatement(sql1);
                pst1.setString(1, SOURCE);
                pst1.setString(2, DESTINATION);
                ResultSet rs1 = pst1.executeQuery();
                boolean routeFound = false;

                while(rs1.next())
                {
                    routeFound = true;
                    System.out.println();
                    price = rs1.getDouble("TicketPrice");
                    trainId = rs1.getInt("trainId");
                    trainName = rs1.getString("TrainName");
                    departureTime = rs1.getString("TrainSourceTime");
                    arrivalTime = rs1.getString("TrainDestinationTime");
                    System.out.println();
                    System.out.println("Train ID: "+rs1.getString("trainId"));
                    System.out.println("Train Name: "+rs1.getString("TrainName"));
                    System.out.println("FROM: "+rs1.getString("Source"));
                    System.out.println("TO: "+rs1.getString("Destination"));
                    System.out.println("Departure Time: "+rs1.getString("TrainSourceTime"));
                    System.out.println("Arrival Time: "+rs1.getString("TrainDestinationTime"));
                    System.out.println("Ticket Price: "+rs1.getString("TicketPrice"));
                    check_routes = true;
                }
                if(!routeFound)
                {
                    System.out.println("No Routes are Available.");
                    continue;
                }
                System.out.println();
                System.out.print("Enter No. of Passengers: ");
                int n = sc.nextInt();
                sc.nextLine();
                for(int j = 0; j<n; j++)
                {
                    System.out.println("\nEnter Details of Passenger : " + (j + 1) + "\n");
                    System.out.print("Enter Passenger First Name : ");
                    firstName = sc.next();
                    System.out.print("Enter Passenger Middle Name : ");
                    middleName = sc.next();
                    System.out.print("Enter Passenger Last Name : ");
                    lastName = sc.next();
                    fullName = firstName+" "+middleName+" "+lastName;
                    do
                    {
                        System.out.print("Enter Passenger Gender (MALE, FEMALE, OTHER): ");
                        gender = sc.next();
                        if (gender.equalsIgnoreCase("MALE") || gender.equalsIgnoreCase("FEMALE") || gender.equalsIgnoreCase("OTHER"))
                        {
                            break;
                        } 
                        else
                        {
                            System.out.println("Invalid gender. Please enter MALE, FEMALE, or OTHER.");
                        }
                    } while (true);
                    do
                    {
                        System.out.print("Enter Passenger Age : ");
                        age = sc.nextInt();
                        if (age >= 0)
                        {
                            break;
                        }
                        else
                        {
                            System.out.println("Invalid age. Please enter a non-negative age.");
                        }
                    } while (true);
                    System.out.print("Enter Passenger Mobile No. : ");
                    System.out.print("+91 ");
                    mobileNo = sc.next();
                    boolean mobile = true;
                    while(mobile)
                    {
                        if (mobileNo.length() == 10)
                        {
                            int count = 0;
                            for (int i = 0; i < 10; i++)
                            {
                                if (mobileNo.charAt(i) >= '0' && mobileNo.charAt(i) <= '9')
                                {
                                    if (i == 9)
                                    {
                                        mobile = false;
                                    }
                                }
                                else
                                {
                                    count++;
                                    break;
                                }
                            }
                            if (count > 0)
                            {
                                System.out.println("Please Enter Your Mobile No. Correctly.\n");
                                System.out.print("Enter Passenger Mobile No. : ");
                                System.out.print("+91 ");
                                mobileNo = sc.next();
                            }
                        }
                        else
                        {
                            System.out.println("Please Enter Correct Mobile No.\n");
                            System.out.print("Enter Passenger Mobile No. : ");
                            System.out.print("+91 ");
                            mobileNo = sc.next();
                        }
                    }
                    check_date = true;
                    while(check_date)
                    {
                        System.out.print("Enter a day: ");
                        day = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter a month: ");
                        month = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter a year: ");
                        year = sc.nextInt();

                        //Vaildate the month
                        if(month<1 || month>12)
                        {
                            System.out.println("You have entered Invalid Month. Please Enter Month correctly.");
                            continue;
                        }

                        // Adjust February Days for Leap Year
                        if(month == 2 && isLeapYear(year))
                        {
                            date.put(2,29);
                        }
                        else
                        {
                            date.put(2,28);
                        }

                        if(day>=1 && day<=date.get(month) && year >= 2024)
                        {
                            check_date = false;
                        }
                        else
                        {
                            System.out.println("You have entered Invaild Date. Please Enter the Date correctly.");
                        }
                    }
                    String dayStr = String.format("%02d", day);
                    String monthStr = String.format("%02d", month);
                    String yearStr = String.valueOf(year);

                    formattedDate = yearStr + "-" + monthStr + "-" + dayStr;
                    
                    // details.clear();
                    details.add(new Passenger(fullName, gender, mobileNo, age, formattedDate));
                    details.clear();
                    sc.nextLine();

                    int tempid = 0;
                    String tempsql = "SELECT LoginId FROM rbs WHERE Username = ?";
                    PreparedStatement pst2 = con.prepareStatement(tempsql);
                    pst2.setString(1, UserLogin.username);
                    ResultSet rs2 = pst2.executeQuery();
                    while(rs2.next())
                    {
                        tempid = rs2.getInt(1);
                        System.out.println("Log-In ID: "+tempid);
                    }

                    String sql3 = "INSERT INTO passengerdetails (LoginId, FullName, Gender, Age, MobileNo) VALUES (?,?,?,?,?)";
                    PreparedStatement pst3 = con.prepareStatement(sql3);
                    pst3.setInt(1, tempid);
                    pst3.setString(2, fullName);
                    pst3.setString(3, gender);
                    pst3.setInt(4, age);
                    pst3.setString(5, mobileNo);
                    pst3.executeUpdate();
                }

                total_price = price*n;
                System.out.println();
                System.out.println("****************************************************************");
                System.out.println("Seat Booking in 2 - TIER AC CLASS (2AC)");
                System.out.println("Consider LOWER BERTH - LB, MIDDLE BERTH - MB, UPPER BERTH = UB");

                System.out.println();
                bookSeat(con);
                System.out.println();
                payment();
                System.out.println("-------------------------------------------");

                String sql4 = "INSERT IGNORE INTO PassengerBooking (BookingId, LoginId, TrainId, TrainName, FullName, Age, Gender, seatNo, Source, Destination, Departure_Time, Arrival_Time, Date_Of_Journey, Ticket_Price) "+ 
                "SELECT sb.BookingId, sb.LoginId, sb.TrainId, sb.TrainName, pd.FullName, pd.Age, pd.Gender, sb.seatNo, sb.Source, sb.Destination, sb.DepartureTime, sb.ArrivalTime, sb.date, sb.TicketPrice "+
                "FROM seatbooking sb "+
                "INNER JOIN passengerdetails pd ON pd.BookingId = sb.BookingId";

                PreparedStatement pst4 = con.prepareStatement(sql4);
                pst4.executeUpdate();
                break;
            }
        }
    }
    public static boolean isLeapYear(int year) // Metod to confirm the year is Leap Year or not.
    {
        return (year%4 == 0 && year%100 !=0) || (year%400 == 0);
    }
    void createSeatMap() // Show The Seat Map Pattern that which seats are booked and which are not.
    {
        int seatNumber = 1;
        System.out.printf("%-20s%-20s%-20s%n", "LB", "MB", "UB");
        for (int i = 0; i < 10; i++)// Assuming 10 rows
        {
           System.out.printf("%-20s%-20s%-20s%n", getSeatLabel("LB", seatNumber), getSeatLabel("MB", seatNumber + 1), getSeatLabel("UB", seatNumber + 2));
            seatNumber += 3;
        }
    }
    String getSeatLabel(String type, int number) //Checks the Seat Label is correct or not for ex: LB01, MB02, UB04,...
    {
        String seatNumberFormatted = String.format("%02d", number);
        String seatLabel = type + seatNumberFormatted;
        if(bookedSeats.contains(seatLabel))
        {
            return seatLabel + " (Booked)";
        }
        else
        {
            return seatLabel;
        }
    }
    boolean isSeatInRange(int seatNumber) // Checks whether a given seat number falls within a specified valid range of seat numbers.
    {
        return seatNumber >= 1 && seatNumber <= 30; // Adjust this range if your seat map has more or fewer seats
    }
    boolean isValidSeat(String seat)
    {
        // Check length
        if(seat.length() != 4)
        {
            return false;
        }
    
        // Get type and number
        String type = seat.substring(0, 2);
        String numberStr = seat.substring(2); // The variable `numberStr` is of type String
    
        // Check if type is valid and number format is correct
        if((type.equals("LB") || type.equals("MB") || type.equals("UB")) && numberStr.matches("\\d{2}"))
        {
            int number = Integer.parseInt(numberStr);
            return isSeatInRange(number) && isSeatValidForType(type, number); // Check if the seat number is within the valid range
        }
        return false;
    }
    boolean isSeatValidForType(String type, int number) // Check if the seat number matches the type's expected pattern.
    {
        if (type.equals("LB")) {
            return number % 3 == 1;
        } else if (type.equals("MB")) {
            return number % 3 == 2;
        } else if (type.equals("UB")) {
            return number % 3 == 0;
        }
        return false;
    }
    void bookSeat(Connection con) throws Exception
    {
        fetchBookedSeats(con);

        System.out.println("Available Seats:");
        createSeatMap();

        System.out.println("Enter seat numbers to book (e.g., LB01, MB02, UB03). Type 'DONE' to finish:");
        while (true)
        {
            String seat = sc.next().toUpperCase();
            if(seat.equals("DONE"))
            {
                break;
            }
            if(isValidSeat(seat))
            {
                if(bookedSeats.contains(seat))
                {
                    System.out.println("Sorry, " + seat + " is already booked.");
                }
                else
                {
                    bookedSeats.add(seat);
                    storeBooking(con, seat);
                    System.out.println("Seat " + seat + " successfully booked.");
                }
            }
            else
            {
                System.out.println("Invalid seat code. Please enter a valid seat code.");
            }
        }
    }
    void fetchBookedSeats(Connection con) throws Exception
    {
        String sql = "SELECT seatNo FROM seatbooking WHERE date = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, formattedDate);
        ResultSet rs = pst.executeQuery();

        bookedSeats.clear(); // Clear previous data
        while (rs.next())
        {
            bookedSeats.add(rs.getString("seatNo"));
        }
    }
    void payment()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("****************************************************************");
        System.out.println("\nHere total 2 types of payment methods are avaliable.\nPress 1: Credit Card\nPress 2: Debit Card\n");
        int pay = sc.nextInt();

        boolean credit = true;
        boolean debit = true;
        boolean debit_check = true;
        boolean payment_method = true;
        int n = 0;
        while (payment_method) 
        {
            switch (pay) 
            { 
                case 1:
                    
                    while (credit) 
                    {
                        System.out.println("Enter your Credit Card number:");
                        String credit_no = sc.next();
                        if(credit_no.length()==16)
                        {
                            for(l=0; l<16; l++)
                            {
                                if(credit_no.charAt(l)>='0' && credit_no.charAt(l)<='9')
                                {   
                                    if(l==15)
                                    {
                                        credit = false;
                                    }
                                    continue;
                                }
                                else
                                {
                                    System.out.println("\nPlease enter correct Credit Card no.\n");
                                    break;
                                }
                            }
                        }
                        else
                        {
                            System.out.println("\nCredit Card no. length must be 16 digits only\n");
                        }    
                    }
                    if(l==16)
                        {
                            System.out.println("\nYou have to pay " + total_price + "/- to book a Ticket\n");
                            System.out.println("Enter amount to pay Ticket Price:");
                            amount = sc.nextDouble();
                            if(amount==total_price)
                            {
                                System.out.println("Transaction Successful...");
                                payment_method = false;
                                ticket_booked = true;
                                ticket_status = 1;
                                
                            }
                            else
                            {
                                System.out.println("You have entered insufficient amount , Please enter sufficient amount\n");
                            } 
                        }
                    break;

                case 2: 
                
                    while (debit_check) 
                    {
                        
                        System.out.println("Enter your Debit Card number:");
                        String debit_no = sc.next();
                        if(debit_no.length()==12)
                        {
                            for(int k=0; k<12; k++)
                            {
                                if(debit_no.charAt(k)>='0' && debit_no.charAt(k)<='9')
                                {   
                                    if(k==11)
                                    {
                                        debit_check = false;
                                    }
                                    continue;
                                }
                                else
                                {
                                    System.out.println("Please enter correct Debit Card no.\n");
                                    break;
                                }
                            }
                        }
                        else
                        {
                            System.out.println("Debit Card no. length must be 12 digits only\n");
                        }    
                    }
                        while (debit) 
                        {
                            
                            System.out.println("Enter your Debit Card Password\n");
                            String debit_password = sc.next();
                            n = debit_password.length();
                            if(n==6 || n==4)
                            {
                                for(l=0; l<n; l++)
                                {
                                    if(debit_password.charAt(l)>='0' && debit_password.charAt(l)<='9')
                                    {   
                                        if(l==(n-1))
                                        {
                                            debit = false;
                                        }
                                        continue;
                                    }
                                    else
                                    {
                                        System.out.println("Please enter correct Debit Card password\n");
                                        break;
                                    }
                                }
                            }
                            else
                            {
                                System.out.println("Debit Card password length must be 4 or 6 digits only.\n");
                            }    
                        }
                        if(l==n)
                            {
                                System.out.println("\nYou have to pay " + total_price + "/- to book a ticket\n");
                                System.out.println("Enter amount to pay Ticket Price:");
                                amount = sc.nextDouble();
                                if(amount==total_price)
                                {
                                    System.out.println("Transaction Successful...");
                                    payment_method = false;
                                    ticket_booked = true;
                                    ticket_status = 1;
                                }
                                else
                                {
                                    System.out.println("You have entered insufficient amount , Please enter sufficient amount");
                                } 
                            }
                        break;
						
					default:
                    System.out.println("Invalid choice, Please Enter a valid choice");
                    break;
		    }
		}
	}
    void storeBooking(Connection con, String seatNo) throws Exception
    {
        int tempid = 0;
        String tempsql = "SELECT LoginId FROM rbs WHERE Username = ?";
        PreparedStatement pst2 = con.prepareStatement(tempsql);
        pst2.setString(1, UserLogin.username);
        ResultSet rs1 = pst2.executeQuery();
        while(rs1.next())
        {
            tempid = rs1.getInt(1);
            System.out.println("Log-In ID: "+tempid);
        }

        String sql = "INSERT INTO seatbooking (LoginId, TrainId, TrainName, seatNo, Source, Destination, DepartureTime, ArrivalTime, date, TicketPrice) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, tempid);
        pst.setInt(2, trainId);
        pst.setString(3, trainName);
        pst.setString(4, seatNo);
        pst.setString(5, SOURCE);
        pst.setString(6, DESTINATION);
        pst.setString(7, departureTime);
        pst.setString(8, arrivalTime);
        pst.setString(9, formattedDate);
        pst.setDouble(10, price);
        pst.executeUpdate();
    }
    void viewTicket(Connection con) throws Exception
    {
        System.out.println("Enter Your LogIn-ID to View your Booked Ticket:");
        int LoginID = sc.nextInt();
        System.out.println();
        String vt = "SELECT FullName, Gender, Age, BookingId, TrainId, TrainName, Source, Destination, Departure_Time, Arrival_Time, Date_Of_Journey, seatNo, Ticket_Price FROM PassengerBooking WHERE LoginId = "+LoginID;
        PreparedStatement pst = con.prepareStatement(vt);
        ResultSet rs = pst.executeQuery();
        while(rs.next())
        {
            System.out.println("|-------------------------------------- TICKET CONFIRMATION --------------------------------------|");
            System.out.print("\n");
            System.out.println("Passenger Name: "+rs.getString("FullName")+"                                "+"Date Of Journey: "+rs.getString("Date_Of_Journey"));
            System.out.println("Sex: "+rs.getString("Gender"));
            System.out.println("Age: "+rs.getInt("Age")+"\n");
            System.out.println("Booking ID: "+rs.getInt("BookingId"));
            System.out.println("Train ID: "+rs.getInt("TrainId"));
            System.out.println("Train Name: "+rs.getString("TrainName")+"\n");
            System.out.print(rs.getString("Source")+" JN."+" ----------------- TO  -----------------> "+rs.getString("Destination")+" JN."+"\n");
            System.out.println();
            System.out.println("Departure Time: "+rs.getString("Departure_Time"));
            System.out.println("Arrival Time: "+rs.getString("Arrival_Time"));
            System.out.println("Seat Berth & No.: "+rs.getString("seatNo")+"\n");
            System.out.println("Ticket Price(per person) : Rs."+rs.getString("Ticket_Price")+"/-\n");
            System.out.println("|----------------------------------------- HAPPY JOURNEY -----------------------------------------|\n");
            System.out.println();
            System.out.println();
        }
        System.out.println("-------------------------------------------");
    }
    void cancelTicket(Connection con) throws Exception
    {
        double p_fare;
        int b_id, l_id, t_id, p_age;
        String t_name, p_name, p_gender, p_seatNo, p_Source, p_Destination, p_Departure_Time, p_Arrival_Time, p_Date_Of_Journey;

        System.out.print("To Cancel Your Ticket please enter your Booking ID: ");
        int bookingId = sc.nextInt();
        sc.nextLine();
        String cb = "SELECT * FROM PassengerBooking WHERE BookingId = "+bookingId;
        PreparedStatement pst6 = con.prepareStatement(cb);
        ResultSet rs6 = pst6.executeQuery();
        while(rs6.next())
        {
            b_id = rs6.getInt("BookingId");
            l_id = rs6.getInt("LoginId");
            t_id = rs6.getInt("TrainId");
            t_name = rs6.getString("TrainName");
            p_name = rs6.getString("FullName");
            p_age = rs6.getInt("Age");
            p_gender = rs6.getString("Gender");
            p_seatNo = rs6.getString("seatNo");
            p_Source = rs6.getString("Source");
            p_Destination = rs6.getString("Destination");
            p_Departure_Time = rs6.getString("Departure_Time");
            p_Arrival_Time = rs6.getString("Arrival_Time");
            p_Date_Of_Journey = rs6.getString("Date_Of_Journey");
            p_fare = rs6.getDouble("Ticket_Price");   
            p_fare = p_fare*0.25;

            String ct = "INSERT INTO CancelBooking (BookingId, LoginId, TrainId, TrainName, FullName, Age, Gender, seatNo, Source, Destination, Departure_Time, Arrival_Time, Date_Of_Journey, Refund_Price) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst7 = con.prepareStatement(ct);
            pst7.setInt(1, b_id);
            pst7.setInt(2, l_id);
            pst7.setInt(3, t_id);
            pst7.setString(4, t_name);
            pst7.setString(5, p_name);
            pst7.setInt(6, p_age);
            pst7.setString(7, p_gender);
            pst7.setString(8, p_seatNo);
            pst7.setString(9, p_Source);
            pst7.setString(10, p_Destination);
            pst7.setString(11, p_Departure_Time);
            pst7.setString(12, p_Arrival_Time);
            pst7.setString(13, p_Date_Of_Journey);
            pst7.setDouble(14, p_fare);

            pst7.executeUpdate();
        }
        System.out.println();
        System.out.println("Booking Cancelled for Booking ID: "+bookingId);
        System.out.println();

        String refundPrice = "SELECT Ticket_Price FROM PassengerBooking WHERE BookingId = "+bookingId;
        PreparedStatement pst8 = con.prepareStatement(refundPrice);
        ResultSet rs8 = pst8.executeQuery();
        while(rs8.next())
        {
            System.out.println("Refunded Amount: Rs. "+(rs8.getDouble("Ticket_Price")*0.25)+"/-\n");
        }
        String delete1 = "DELETE FROM PassengerBooking WHERE BookingId = "+bookingId;
        PreparedStatement pst9 = con.prepareStatement(delete1);
        pst9.executeUpdate();
        System.out.println();
        String delete2 = "DELETE FROM passengerdetails WHERE BookingId = "+bookingId;
        PreparedStatement pst10 = con.prepareStatement(delete2);
        pst10.executeUpdate();
        System.out.println();
        String delete3 = "DELETE FROM seatbooking WHERE BookingId = "+bookingId;
        PreparedStatement pst11 = con.prepareStatement(delete3);
        pst11.executeUpdate();
        System.out.println();

        System.out.println("--------------------------------------------");
    }
}

abstract class ViewBooking
{
    abstract void viewTicket(Connection con) throws Exception;
}

class RailwayBookingSystem2
{
    public static void main(String[] args) throws Exception {
        
        String dburl = "jdbc:mysql://localhost:3306/railway";
        String dbuser = "root"; // by default username.
        String dbpass = "";
        String drivername = "com.mysql.cj.jdbc.Driver";
        Class.forName(drivername);
        Connection con = DriverManager.getConnection(dburl, dbuser, dbpass);
        if(con != null)
        {
            System.out.println("Connection Successfull.\n");
        }
        else
        {
            System.out.println("Connection Failed.");
        }

        Scanner sc = new Scanner(System.in);

        UserLogin log_In = new UserLogin();
        
        TicketBooking ticketBook = new TicketBooking();

        System.out.println("-------------------------- Welcome To Indian Railway Catering & Tourism Corporation... --------------------------\n");
        int ch;
        do
        {
            System.out.println("\nPress 1: IRCTC Login\nPress 2: Ticket Booking\nPress 3: Show a Ticket\nPress 4: Cancel Booking\nPress 5: Exit the System.\n-------------------------------------------");
            ch = sc.nextInt();

            switch(ch)
            {
                case 1:
                {
                    System.out.print("Do You Want To Search Your Existing Login ID? (YES/NO): ");
                    String confirm = sc.next();
                    if(confirm.equalsIgnoreCase("YES"))
                    {
                        System.out.println("Enter Your Username: ");
                        String oldusername = sc.next();
                        String sql1 = "SELECT LoginId FROM rbs WHERE Username = ?";
                        PreparedStatement pstmt1 = con.prepareStatement(sql1);
                        pstmt1.setString(1, oldusername);
                        ResultSet rs1 = pstmt1.executeQuery();
                        if(rs1.next())
                        {
                            System.out.println("Log-In ID: "+rs1.getInt("LoginId"));
                        }
                        else
                        {
                            System.out.println("No such Username Found! Please Create your Account first.\n");
                            log_In.userLogin(con);
                        }
                    }
                    else
                    {
                        System.out.println("Please Create your Account first.\n");
                        log_In.userLogin(con);
                    }
                    break;
                }
                case 2:
                {
                    if(log_In.isLoggedIn())
                    {
                        try
                        {
                            ticketBook.bookTicket(con);
                        }
                        catch(Exception e)
                        {
                            System.out.println("Error Occured: "+e.getMessage());
                        }
                        log_In.loggedIn = false;
                        break;
                    }
                    else
                    {
                        System.out.println("\nError! Please Log-In to book your Ticket.");
                        break;
                    }
                }
                case 3:
                {
                    ViewBooking view_ticket_booking = new TicketBooking();
                    view_ticket_booking.viewTicket(con);
                    break;
                }
                case 4:
                {
                    ticketBook.cancelTicket(con);
                    break;
                }
                case 5:
                {
                    System.out.println("Thank You! Visit Again...");
                    break;
                }
                default:
                {
                    System.out.println("Invalid Input!");
                }
            }
        }while(ch != 5);
    }
}