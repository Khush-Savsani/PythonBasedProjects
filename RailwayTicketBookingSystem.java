import java.util.*;
class Railway
{
	String to, from, TO, FROM, f_Name, m_Name, l_Name, seat_no, mo_no, duration;
    int day, month, year, person, i=0, ticket_status;
    int train_price, total_price, ammount;
    boolean payment_method = true, ticket_booked = false;
	Railway []a;
	String r1 = "AHMEDABAD", r2 = "AYODHYA", r3 = "BENGALURU", r4 = "CHENNAI", r5 = "DELHI", r6 = "HYDERABAD", r7 = "KOLKATA", r8 = "MATHURA", r9 = "MUMBAI", r10 = "SURAT";
	void ticket_booking()
	{
		Scanner sc = new Scanner(System.in);
		
		boolean c = true;
		while(c)
		{
			System.out.println("\nHere Only 10 Routes are available.");
			System.out.println("1. "+r1+"  "+"2. "+r2+"  "+"3. "+r3+"  "+"4. "+r4+"  "+"5. "+r5+"  "+"6. "+r6+"  "+"7. "+r7+"  "+"8. "+r8+"  "+"9. "+r9+"  "+"10. "+r10);
			System.out.println();
			
			boolean check_routes = true;
			while(check_routes)
			{
				System.out.print("TO : ");
				to = sc.next();
				TO = to.toUpperCase();
				TO = TO.replaceAll("\\s+", ""); //It is used to eliminate extra spaces if a user has accidentally typed it.
				if(TO.equals(r1)||TO.equals(r2)||TO.equals(r3)||TO.equals(r4)||TO.equals(r5)||TO.equals(r6)||TO.equals(r7)||TO.equals(r8)||TO.equals(r9)||TO.equals(r10))
				{ 
					check_routes = false;
				}
				else
				{
					System.out.println("Routes are Invalid. Enter Valid Routes.");
				}
			}
			check_routes = true;
			while(check_routes)
			{
				System.out.print("FROM : ");
				from = sc.next();
				FROM = from.toUpperCase();
				FROM = FROM.replaceAll("\\s+", ""); //It is used to eliminate extra spaces if a user has accidentally typed it.
				if(FROM.equals(r1)||FROM.equals(r2)||FROM.equals(r3)||FROM.equals(r4)||FROM.equals(r5)||FROM.equals(r6)||FROM.equals(r7)||FROM.equals(r8)||FROM.equals(r9)||FROM.equals(r10))
				{
					check_routes = false;
				}
				else
				{
					System.out.println("Routes are Invalid. Enter Valid Routes.");
				}
			}
			if(TO.equals(FROM))
			{
				System.out.println("\n\"You Have entered invalid Routes. Please Enter Routes Carefully.\"");
			}
			else
			{
				boolean mobile = true;
				while(mobile)
				{
					System.out.println("\n Enter Your Mobile No.");
					System.out.print("+91 ");
					mo_no = sc.next();
					if(mo_no.length()==10)
					{
						int count = 0;
						for(int i = 0; i<10; i++)
						{
							if(mo_no.charAt(i)>='0' && mo_no.charAt(i)<='9')
							{
								if(i==9)
								mobile = false;
							}
							else
							{
								count++;
								break;
							}
						}
						if(count>0)
						{
							System.out.println("Please Enter Your Mobile No. Correctly.");
						}
					}
					else
					{
						System.out.println("Please Enter Correct Mobile No.");
					}
				}
				boolean check_date = true;
				while(check_date)
				{
					System.out.println("Enter a day");
                    day = sc.nextInt();
                    System.out.println("Enter a month");
                    month = sc.nextInt();
                    System.out.println("Enter a year");
                    year = sc.nextInt(); 
					
					switch(month)
					{	
						case 1:
						case 3:
						case 5:
						case 7:
						case 8:
						case 10:
						case 12:
					
						if((day>=1 && day<=31) && year>=2024)
						{
							check_date = false;
						}
						else
						{
							System.out.println("You have entered invalid date. Please make it correct.");
						}
						break;
					
						case 4:
						case 6:
						case 9:
						case 11:
					
						if((day>=1 && day<=31) && year>=2024)
						{
							check_date = false;
						}
						else
						{
							System.out.println("You have entered invalid date. Please make it correct.");
						}
						break;
					
						case 2:
						if((year%4==0) || (year%400 == 0 && year%100 ==0))
						{
							if ((day>=1 && day<=29) && year>=2024)
							{
								check_date = false;
							}
							else
							{
								System.out.println("You have entered invalid date. Please make it correct.");
							}
						}
						else if((day>=1 && day<=28) && year>=2024)
						{
							check_date = false;
						}
						else
						{
							System.out.println("You have entered invalid date. Please make it correct.");
						}
						break;
						
						default :
						System.out.println("You have entered invalid month. Please make it correct.");
						break;
					}
				}
				System.out.println("Enter Total Number of Person(s)");
				person = sc.nextInt();
				
				a = new Railway[person];
				for(i = 0; i<person; i++)
				{
					a[i] = new Railway(); // Start Object Creation
					System.out.println("~~~~~~~~~~~~ Enter detail(s) for person "+(i+1)+ " ~~~~~~~~~~~~" );
					a[i].details(); //call detail() function/method.
				}
				System.out.println();

				boolean check_class = true;
				while(check_class)
				{
					System.out.println("Press 1 for 2 TIER AC CLASS (2A)");
					
					System.out.println("\nEnter Your Choice: \n");
					int f = sc.nextInt(); 
					
					if(f==1) // Condition For 2 TIER AC CLASS
					{
						System.out.println("\nYou have selected Booking process for 2 TIER AC CLASS (2A) in BHARTIYA Railway Reservation System:");
						System.out.println("\nLower Berth - LB, Upper Berth - UB, Side Lower Berth - SLB, Side Upper Berth - SUB\n");

						// For loop to iterate over each seat
						for (int i = 1; i <= 48; i++) //Loop for 2 TiER AC CLASS
						{
							// Print berth type with appropriate spacing
							if (i % 4 != 0)
							{
								if (i % 4 == 1)
								{
									System.out.print("LB" + String.format("%02d", i));
								}
								else if (i % 4 == 2)
								{
									System.out.print("UB" + String.format("%02d", i));
								}
								else if (i % 4 == 3)
								{
									System.out.print("  ");
									System.out.print("SLB" + String.format("%02d", i));
								}
								else
								{
									System.out.print("SUB" + String.format("%02d", i));
								}
								System.out.print("  ");
							}
							else
							{
								if (i % 4 == 1)
								{
									System.out.println("LB" + String.format("%02d", i));
								}
								else if (i % 4 == 2)
								{
									System.out.println("UB" + String.format("%02d", i));
								}
								else if (i % 4 == 3)
								{
									System.out.println("SLB" + String.format("%02d", i));
								}
								else
								{
									System.out.println("SUB" + String.format("%02d", i));
								}
							}
						}
					}
					else
					{
						System.out.println("You Have Entered Invalid class. Please Enter class from the above given class\n");
					    continue;
					}
					for(i=0; i<person; i++)
					{
						System.out.println("Select a seat for Person " + (i+1) + " As per shown above");
						a[i].seat_selection();// call seat_selection() function/method.
					}
					check_class = false;
					price_set();//call price set function/method.
					c = false;
				}
			}
		}
	}
	void details()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter First Name");
		f_Name = sc.next();
		System.out.println("Enter Middle Name");
		m_Name = sc.next();
		System.out.println("Enter Last Name");
		l_Name = sc.next();
	}
	boolean seat_Booked;
    String seat_Type;
    int booked_SeatNumber;

    void seat_selection()
	{
        Scanner sc = new Scanner(System.in);
        boolean valid_Seat = false;
		booked_SeatNumber = -1;
        while (!valid_Seat)
		{
            System.out.println("\nEnter Seat Type (LB/UB/SLB/SUB): ");
            seat_Type = sc.next().toUpperCase();

            select_Seat();

            if (booked_SeatNumber != -1)
			{
                valid_Seat = true;
                seat_Booked = true;
                System.out.println("\nSeat " + seat_Type + String.format("%02d", booked_SeatNumber) + " has been booked.\n");
            }
			else
			{
                System.out.println("\nSelected seat type is fully booked or invalid. Please choose another seat type.\n");
            }
        }
    }

    void select_Seat()
	{
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the seat number you want to book: ");
        int seat_Number = sc.nextInt();

        if (seat_Number >= 1 && seat_Number <= 48 && !seat_Booked && is_SeatTypeValid(seat_Number))
		{
            booked_SeatNumber = seat_Number;
        }
		else
		{
            booked_SeatNumber = -1;
			System.out.println("Seat selection failed or invalid seat.");
        }
    }

    boolean is_SeatTypeValid(int seat_Number)
	{
        if (seat_Type.equals("LB") && seat_Number % 4 == 1)
		{
            return true;
        }
		else if (seat_Type.equals("UB") && seat_Number % 4 == 2)
		{
            return true;
        }
		else if (seat_Type.equals("SLB") && seat_Number % 4 == 3)
		{
            return true;
        }
		else
		{
			return seat_Type.equals("SUB") && seat_Number % 4 == 0;
		}
    }
	void price_set()
	{
		Scanner sc = new Scanner(System.in);
		switch(TO)
		{
			case "AYODHYA":
				train_price = 2300;
				duration = "29hrs 12min | 2 stops : stop-1(UDZ), stop-2(JHS)";
				break;
			
			case "BENGALURU":
				train_price = 2620;
				duration = "32hrs 35min | 2 stops : stop-1(MMCT), stop-2(SUR)";
				break;
				
			case "CHENNAI":
				train_price = 2860;
				duration = "31hrs 30min | 3 stops : stop-1(JL), stop-2(AMI), stop-3(BZA)";
				break;
				
			case "DELHI":
				train_price = 1800;
				duration = "14hrs 45min | 3 stops : stop-1(JP), stop-2(AWR), stop-3(GGN)";
				break;
			
			case "HYDERABAD":
				train_price = 2170;
				duration = "21hrs 15min | 2 stops : stop-1(ST), stop-2(AMI)";
				break;
				
			case "KOLKATA":
				train_price = 3030;
				duration = "37hrs 25min | 3 stops : stop-1(BPL), stop-2(BSB), stop-3(PNBE)";
				break;
			
			case "MATHURA":
				train_price = 1850;
				duration = "12hrs 55min | 2 stops : stop-1(BRC), stop-2(KOTA)";
				break;
				
			case "MUMBAI":
				train_price = 1300;
				duration = "7hrs 25min | 2 stops : stop-1(BRC), stop-2(ST)";
				break;
				
			case "SURAT":
				train_price = 1000;
				duration = "4hr 10min | non stop";
				break;
				
			default:
                train_price = 2000;
                duration = "2h 45m | non stop";
                break;
		}
		System.out.println("\n"+FROM + " TO " + TO + " Ticket Price per Person : " + train_price + "/- \n");
        total_price = train_price*person;
        System.out.println("\nTotal Price : " + total_price + "/-\n");
        System.out.println("\nTravelling Duration : " + duration+"\n");
		
		payment();
	}
	void payment()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nHere total 2 types of payment methods are avaliable.\nPress 1 for Credit Card\nPress 2 for Debit Card\n");
        int pay = sc.nextInt();

        boolean credit = true;
        boolean debit = true;
        boolean debit_check = true;
        int n = 0;
        while (payment_method) 
        {
            switch (pay) 
            { 
                case 1:
                    
                    while (credit) 
                    {
                        System.out.println("Enter your credit card number");
                        String credit_no = sc.next();
                        if(credit_no.length()==16)
                        {
                            for(i=0; i<16; i++)
                            {
                                if(credit_no.charAt(i)>='0' && credit_no.charAt(i)<='9')
                                {   
                                    if(i==15)
                                    {
                                        credit = false;
                                    }
                                    continue;
                                }
                                else
                                {
                                    System.out.println("\nPlease enter correct Credit card no.\n");
                                    break;
                                }
                            }
                        }
                        else
                        {
                            System.out.println("\nCredit card no. length must be 16 digits only\n");
                        }    
                    }
                    if(i==16)
                        {
                            System.out.println("\nYou have to pay " + total_price + "/- to book a ticket\n");
                            System.out.println("Enter ammount for pay ticket price");
                            ammount = sc.nextInt();
                            if(ammount==total_price)
                            {
                                System.out.println("Transaction successful");
                                payment_method = false;
                                ticket_booked = true;
                                ticket_status = 1;
                            }
                            else
                            {
                                System.out.println("You have entered insufficient ammount , please enter sufficient ammount\n");
                            } 
                        }
                    break;

                case 2: 
                        //int n = 0;
                    while (debit_check) 
                    {
                        
                        System.out.println("Enter your Debit card number\n");
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
                                    System.out.println("Please enter correct Debit card no.\n");
                                    break;
                                }
                            }
                        }
                        else
                        {
                            System.out.println("Debit card no. length must be 12 digits only\n");
                        }    
                    }
                        while (debit) 
                        {
                            
                            System.out.println("Enter your Debit card Password\n");
                            String debit_password = sc.next();
                            n = debit_password.length();
                            if(n==6 || n==4)
                            {
                                for(i=0; i<n; i++)
                                {
                                    if(debit_password.charAt(i)>='0' && debit_password.charAt(i)<='9')
                                    {   
                                        if(i==(n-1))
                                        {
                                            debit = false;
                                        }
                                        continue;
                                    }
                                    else
                                    {
                                        System.out.println("Please enter correct Debit card password\n");
                                        break;
                                    }
                                }
                            }
                            else
                            {
                                System.out.println("Debit card password length must be 4 or 6 digits only\n");
                            }    
                        }
                        if(i==n)
                            {
                                System.out.println("\nYou have to pay " + total_price + "/- to book a ticket\n");
                                System.out.println("Enter ammount for pay ticket price");
                                ammount = sc.nextInt();
                                if(ammount==total_price)
                                {
                                    System.out.println("Transaction successful");
                                    payment_method = false;
                                    ticket_booked = true;
                                    ticket_status = 1;
                                }
                                else
                                {
                                    System.out.println("You have entered insufficient ammount , please enter sufficient ammount");
                                } 
                            }
                        break;
						
					default:
                    System.out.println("Invalid choice, please Enter a valid choice");
                    break;
			}
		}
	}
	void show_ticket()
	{
		System.out.println();
        if(ticket_booked && ticket_status==1)
        {
            System.out.print("------------------- TICKET CONFIRMATION -------------------\n");
            System.out.print("\n");
            System.out.println(FROM + "            TO            " + TO);
            System.out.println("Train no. : 98377");
            System.out.println("CLASS : 2 TIER AC CLASS(2A)");
            for(int k=0; k<person; k++)
            {
                System.out.println("\nName   : " + a[k].f_Name + " " + a[k].m_Name + " " + a[k].l_Name);
				System.out.println("Seat No. : " + a[k].seat_Type + String.format("%02d", a[k].booked_SeatNumber));
            }
            System.out.println("Mobile No : " + mo_no);
            System.out.println("Date  : " + day + "/" + month + "/" + year);
            System.out.println("Duration  : " + duration);
            System.out.println("\n----------------------- HAPPY JOURNEY -----------------------");
        }
        else
        {
            System.out.println("You haven't booked Ticket, please book your ticket first.");
        }
	}
}
class Main
{
	public static void main(String[] args)
	{
		String UID, password, confirm_password;
		Scanner sc = new Scanner(System.in);
		Railway r = new Railway();
		
		System.out.println("~~~~~~~~~~~~~~~~ Welcome to the Login Page ~~~~~~~~~~~~~~~~");
		System.out.println("\nEnter Your UserId. UserId must be of length greater than or equal to '7' ");
		boolean UID_length = true, check_password = true;
		
		while(UID_length)
		{
			System.out.print("Enter UserId : \n");
            UID = sc.next();
			if(UID.length()>=7)
			{
				System.out.println("\nEnter Your Password. Password must be of length greater than or equal to '8' ");
				System.out.print("Enter Password : ");
				password = sc.next();
				if(password.length()>=8)
				{
					while(check_password)
					{
						System.out.println("\nConfirm Your Password.");
						System.out.print("Enter Confirm Password : ");
						confirm_password = sc.next();
						if(password.equals(confirm_password))
						{
							UID_length = false;
							check_password = false;
						}
						else
						{
							System.out.println("\nConfirm Password is invalid. Please enter Confirm Passwored carefully");
						}
					}
				}
				else
				{
					System.out.println("\nPassword is invalid. Please enter Passwored carefully");
				}
			}
			else
			{
				System.out.println("\nUser ID is invalid. Enter User ID carefully.");
			}
		}
		System.out.println("\n**************** WELCOME TO THE BHARTIYA RAILWAY TICKET BOOKING SYSTEM ****************");
		
		boolean b = true;
		while(b)
		{
			System.out.println("\nPress 1 to book a Train Ticket");
            System.out.println("Press 2 to show your Train Ticket");
            System.out.println("Press 3 to exit the System\n");
			
			System.out.println("\n Enter Your Choice: ");
			int choice = sc.nextInt();
			
			switch(choice)
			{
				case 1:
				r.ticket_booking();
				break;
			
				case 2:
				r.show_ticket();
				break;
			
				case 3:
				b = false;
				System.out.println("Thank You Visit Again.");
				break;
			
				default:
				System.out.println("Your Choice is Invalid. Please Enter the Valid Choice.");
				break;
			}
		}
	}
}