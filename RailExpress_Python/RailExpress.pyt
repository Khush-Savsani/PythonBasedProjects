import re
import random
from datetime import datetime
from datetime import date

class RailExpress:
    def __init__(self):
        self.ticketBook = False
        self.Formatted_Date=""

    def signUp(self):
        username = ""
        password = ""
        confirmpassword = ""
        signup = True
        email_verify=re.compile(r'^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$')
        pw1=re.compile(r'\d+')
        pw2=re.compile(r'\w+')
        pw3=re.compile(r'\W+')
        while True:
            try:
                while signup:
                    print("\nLog-In:\n")
                    username = input("E-mail (Please include an '@' in the email address.): ")

                    # Verifying E-mail through regex
                    if(email_verify.match(username)):
                        while True:
                            # Verifying Password through regex
                            password=input("\nPassword (must be greater than 8 characters.): ")
                            if(len(password)>=8 and len(password)>=8 and pw1.search(password) and pw2.search(password) and pw3.search(password)):

                                while True:
                                    # Confirming the password as it is same as password or not
                                    confirmpassword=input("\nConfirm Your Password: ")
                                    if(password==confirmpassword):
                                        break
                                    else:
                                        print("\nPassword does not match. Please try again.\n")
                                        continue
                                break
                            else:
                                print("\nPassword is not valid. Please try again.\n")
                        break
                    else:
                        print("\nInvalid E-mail. Please try again.\n")
                        continue
                self.ticketBook = True
                break

            except KeyboardInterrupt:
                print("\nInvalid input. Please enter a valid email address.\n")
                break

    def ticketBooking(self):
        # List of 10 Routes only
        routes = ["AHMEDABAD", "DWARKA", "JUNAGADH", "MATHURA", "MUMBAI", "SURAT", "KOLKATA", "CHENNAI", "NEW DELHI", "SRINAGAR"]
        self.No_Of_Passenger=0

        # Check if you not signed up then it takes to do sign-up in sign-up function
        if (self.ticketBook == False):
            print("\nYou haven't logged-in. Please do the Log-In first.\n")
            self.signUp()

        # If you signed up then you can do further booking related activities
        if (self.ticketBook == True):
            self.ticketBook = False
            print("\nWelcome to the RailExpress Ticket Booking Page:\n")

            while True:
                print("\nHere we have only 10 routes available.\n")
                print("\n1. AHMEDABAD", "2. DWARKA", "3. JUNAGADH", "4. MATHURA", "5. MUMBAI", "6. SURAT", "7. KOLKATA", "8. CHENNAI", "9. NEW DELHI", "10. SRINAGAR\n")

                try:
                    From = input("From (write station name): ").upper().strip()
                    To = input("To (write station name): ").upper().strip()
                    # Checking that both 'From' and 'To' is same or not and routes taken is only from 'routes' list or not.
                    if ((From in routes) and (To in routes) and (From != To)):
                        print("\nYou have successfully selected your route.\n")
                        train_count = 0  # Counter to keep track of matching trains
                        train_details = []  # List to store details of matching trains

                        print()
                        # Open the file in read mode
                        with open('TrainDetails.txt', 'r') as file:
                            lines = file.readlines()  # Read all lines from the file

                            # Iterate through the lines in chunks of 8 (since each train block is separated by a blank line)
                            for i in range(0, len(lines), 8):
                                # Extract details of each train
                                train_from = lines[i].strip().split(": ")[1].strip()  # Line 1: FROM
                                train_to = lines[i + 1].strip().split(": ")[1].strip()  # Line 2: TO

                                # Check if the 'From' and 'To' match the user input
                                if train_from == From and train_to == To:
                                    train_count += 1
                                    train_details.extend([lines[i].strip(),  # FROM
                                        lines[i + 1].strip(),  # TO
                                        lines[i + 2].strip(),  # TRAIN NAME
                                        lines[i + 3].strip(),   # TRAIN ID
                                        lines[i + 4].strip(),   # FARE
                                        lines[i + 5].strip(),   # ARRIVAL TIME
                                        lines[i + 6].strip()   # DEPARTURE TIME
                                    ])
                                    # Display train overview
                                    print("Train ",train_count," is available:")
                                    print(lines[i].strip())  # FROM
                                    print(lines[i + 1].strip())  # TO
                                    print(lines[i + 2].strip())  # TRAIN NAME
                                    print(lines[i + 3].strip())  # TRAIN ID
                                    print(lines[i + 4].strip())  # FARE
                                    print(lines[i + 5].strip())  # ARRIVAL TIME
                                    print(lines[i + 6].strip())  # DEPARTURE TIME
                                    self.Fare = lines[i+4].strip()

                                    self.From = lines[i].strip()
                                    self.To = lines[i+1].strip()
                                    self.Train_Name = lines[i+2].strip()
                                    self.Train_ID = lines[i+3].strip()
                                    self.Arrival_Time = lines[i+5].strip()
                                    self.Departure_Time = lines[i+6].strip()

                                    print()  # Blank line for readability

                            # If no trains are found, display a message
                            if train_count == 0:
                                print("No trains are available for the specified route.")
                                break

                        # Prompt user to select a train
                        while True:
                            selected_train = int(input("\nEnter the train number (1-" + str(train_count) + ") you want to book: "))

                            if 1 <= selected_train <= train_count:
                                selected_train_index = (selected_train - 1) * 7  # Each train has 7 details
                                print("\nTrain Details:\n")
                                print("\n".join(train_details[selected_train_index:(selected_train_index + 7)]))
                                
                                
                                while True:
                                    
                                    
                                        No_of_passenger = int(input('\nEnter No. of Passengers: ')) # Prompt user for no. of passenger
                                        self.No_Of_Passenger = No_of_passenger
                                        self.passenger_list = []  # List to store details for each passenger
                                        self.passenger_list.clear()
                                        for j in range(No_of_passenger):
                                            Full_name=""

                                            while True:
                                                try:
                                                    print('\nEnter Details of Passenger:',(j+1),"\n")
                                                    First_name = input('Enter Passenger First Name: ').upper().strip()
                                                    Middle_name = input('Enter Passenger Middle Name: ').upper().strip()
                                                    Last_name = input('Enter Passenger Last Name: ').upper().strip()

                                                    
                                                    if(First_name.isalpha() and Middle_name.isalpha() and Last_name.isalpha()):
                                                        Full_name = First_name+" "+Middle_name+" "+Last_name
                                                        break
                                                    else:
                                                        print('Invalid Name. Please Enter Valid Name (Use Alphabets only).')
                                                        continue
                                                except:
                                                    print('Invalid Input. Please Enter Valid Name (Use Alphabets only).')
                                            while True:
                                                try:
                                                    Gender = input("Enter Passeger Gender (MALE, FEMALE, OTHER): ").upper().strip()

                                                    if(Gender=="MALE" or Gender=="FEMALE" or Gender=="OTHER"):
                                                        break
                                                    else:
                                                        print("\nInvalid Input. Please enter MALE, FEMALE or OTHER\n")
                                                        continue
                                                except:
                                                    print("\nInvalid Input. Please enter valid Gender.\n")
                                                    continue

                                            while True:
                                                try:
                                                    Age = int(input("Enter Passenger Age: "))
                                                    if(Age>=0):
                                                        break
                                                    else:
                                                        print("\nInvalid Input. Please enter valid non-negative Age.\n")
                                                        continue
                                                except:
                                                    print("\nInvalid Input. Please enter valid Age.\n")
                                                    continue
                                            # Append the current passenger's details to the list
                                            self.passenger_list.append({"Name": Full_name, "Gender": Gender, "Age": Age})
                                        while True:
                                                try:
                                                    self.Mobile_no = input("Enter Passenger Contact No.: +91 ")
                                                    if(len(self.Mobile_no)==10 and self.Mobile_no.isdigit() and (self.Mobile_no.startswith("6") or self.Mobile_no.startswith("7") or self.Mobile_no.startswith("8") or self.Mobile_no.startswith("9"))):
                                                        break
                                                    else:
                                                        print("\nInvalid Input. Please enter valid 10 digit Mobile and that starts with 6,7,8 or 9 series.\n")
                                                        continue
                                                except:
                                                    print("\nInvalid Input. Please enter valid Contact Number.\n")
                                                    continue
                                            
                                        while True:
                                                try:
                                                    date = input("Enter a Date (DD-MM-YYYY): ")
                                                    # Convert input string to a date object
                                                    input_date = datetime.strptime(date, "%d-%m-%Y").date()
                                                    today = datetime.today().date()
                                                    formatted_date = input_date.strftime('%d-%m-%Y')

                                                    # Check if the entered date is today or in the future
                                                    if(input_date>=today):
                                                        print(formatted_date)
                                                        self.Formatted_Date = formatted_date
                                                        break
                                                    else:
                                                        print("\nInvalid Date. Please enter today's date or a future date.\n")
                                                        continue
                                                except ValueError:
                                                    print("\nInvalid Date Format. Please enter a correct date in DD-MM-YYYY format.\n")
                                                    continue
                                        self.seatmap() 
                                        break
                                    
                                break
                            else:
                                print("\nInvalid train number. Please try again.\n")
                                continue

                        break
                    else:
                        print("\nInvalid Route. Please try again.\n")
                        continue

                except KeyboardInterrupt:
                    print("\nInvalid Input. Please enter route names only.\n")
                    continue
    

    def seatmap(self):
        print("Seat Booking in 2 - TIER AC CLASS (2AC)")
        print("Consider LOWER BERTH - LB, MIDDLE BERTH - MB, UPPER BERTH - UB")
        self.total_seat_booked = 0
        # Added: List to store seats booked by the passenger in this session
        
        self.booked_by_passenger = []
        self.booked_by_passenger.clear()
        self.day = self.Formatted_Date  # Use the formatted date
        print(f"Booking for Date: {self.day}")

        while True:  # Keep displaying the seat map until the user enters "DONE"
            booked_seats = []  # Reset booked seats list

            # Read booked seats from file (assuming the format is Date,Seat in the file)
            try:
                with open("BookedSeats.txt", "r") as file:
                    for line in file:
                        date, seat = line.strip().split(",")
                        if date == self.day:
                            booked_seats.append(seat)
            except FileNotFoundError:
                pass

            seat_number = 1
            available_seats = []
            
            # Display available and booked seats
            try:
                print("\nUpdated Seat Map:")
                print("\nSeat Booking in 2 - TIER AC CLASS (2AC)\n")
                print("Consider LOWER BERTH - LB, MIDDLE BERTH - MB, UPPER BERTH - UB\n")
                for i in range(10):  # 10 rows, 3 seats per row
                    for k in ["LB", "MB", "UB"]:
                        seat = f"{k}{seat_number:02d}"  # Format seat as LB01, MB02, etc.
                        if seat in booked_seats:
                            seat_with_status = seat + " (Booked)"
                            print(f"{seat_with_status.ljust(20)}", end="")  
                        else:
                            print(f"{seat.ljust(20)}", end="")  
                            available_seats.append(seat)
                        seat_number += 1
                    print()
                
                # If no seats are available for the current date, allow user to enter a new date.
                if not available_seats:
                    response = input(
                        f"\nAll seats are booked for {self.day}. "
                        "Enter a new date (YYYY-MM-DD) to view available seats or type 'DONE' to exit: "
                    ).strip()
                    if response.upper() == "DONE":
                        print(self.total_seat_booked)
                        self.payment()
                        break
                    else:
                        self.day = response  # Update the date with the new value
                        print(f"\nBooking for Date: {self.day}")
                        continue  # Restart the loop for the new date

                # Let user select a seat
                self.selected_seat = input(
                    "\nEnter seat number to book (e.g., LB01, MB02, UB03) or type 'DONE' to finish: "
                ).upper().strip()

                if self.selected_seat == "DONE":
                    print(self.total_seat_booked)
                    self.payment()
                    break  # Exit the booking loop
                
                # Convert user input to the proper format (e.g., lb1 -> LB01)
                if len(self.selected_seat) == 3:
                    self.selected_seat = self.selected_seat[0:2].upper() + self.selected_seat[2:].zfill(2)

                # Check if the selected seat is valid and available
                if (len(self.selected_seat) == 4 and self.selected_seat[:2] in ["LB", "MB", "UB"] and 
                    self.selected_seat[2:].isdigit() and 1 <= int(self.selected_seat[2:]) <= 30):
                    if self.selected_seat in available_seats:
                        # Store booking in file
                        with open("BookedSeats.txt", "a") as file:
                            file.write(f"{self.day},{self.selected_seat}\n")
                        print(f"Seat {self.selected_seat} booked successfully for {self.day}.")

                        self.total_seat_booked += 1
                        # Added: Save the successfully booked seat for later recording in PassengerDetails.txt
                        self.booked_by_passenger.append(self.selected_seat)
                    else:
                        print("Seat already booked or invalid. Try again.")
                else:
                    print("Invalid seat selection. Please enter a valid seat (e.g., LB01, MB02, UB03). Try again.")
            except Exception as e:
                print("\nAn error occurred. Please try again.\n")
                continue


    def payment(self):
                
        self.Ticket_Fare=self.Fare[10:16].strip()
        today = date.today()
        print("Proceed To Pay: ",float(self.Ticket_Fare)*self.total_seat_booked)
        print("\nPayment Methods Available: 1: Credit Card  2: Debit Card")
        while True:
            try:
                payment_method = int(input("\nEnter your preferred payment method (1/2): "))
                if payment_method == 1:
                    print("\nCredit Card Payment:")
                    credit_card_number = input("Enter your card number: ").strip()
                    credit_exp = input("Enter Card Expiry Date (MM/YY or MM/YYYY): ").strip()
                    cvv = input("Enter CVV: ").strip()

                    # Validation loop for card details
                    while True:
                        try:
                            # Validate card number
                            if not (credit_card_number.isdigit() and (len(credit_card_number) == 16 or len(credit_card_number) == 19)):
                                raise ValueError("\nCard number must be 16 or 19 digits.\n")

                            # Validate expiry date format
                            if "/" not in credit_exp:
                                raise ValueError("\nExpiry date must be in MM/YY or MM/YYYY format.\n")
                            
                            parts = credit_exp.split("/")
                            if len(parts) != 2:
                                raise ValueError("\nExpiry date must be in MM/YY or MM/YYYY format.\n")
                            
                            month = int(parts[0])
                            if month < 1 or month > 12:
                                raise ValueError("\nMonth must be between 01 and 12.\n")
                            
                            # Accept either 2-digit or 4-digit year
                            if len(parts[1]) == 2:
                                year = 2000 + int(parts[1])
                            elif len(parts[1]) == 4:
                                year = int(parts[1])
                            else:
                                raise ValueError("\nYear must be in YY or YYYY format.\n")
                            
                            # Compare with today's month and year
                            if year < today.year or (year == today.year and month < today.month):
                                raise ValueError("\nThe card has expired.\n")
                            
                            # Validate CVV
                            if not (cvv.isdigit() and len(cvv) == 3):
                                raise ValueError("\nCVV must be exactly 3 digits.\n")

                            # If all validations pass:
                            print("\nCard details accepted.\n")
                            print("\nPayment Done Successfully.\n")
                            try:
                                self.Total_Fare = float(self.Ticket_Fare)*self.total_seat_booked
                                print("Total Fare: ",self.Total_Fare)
                            except ValueError:
                                self.Total_Fare = 0.0
                                print("\n Not Converting")
                            break  # Exit the inner loop

                        except ValueError as ve:
                            print("\nInvalid Card Details:", ve,"\n")
                            # Prompt the user to re-enter the details
                            credit_card_number = input("Enter your card number: ").strip()
                            credit_exp = input("Enter Card Expiry Date (MM/YY or MM/YYYY): ").strip()
                            cvv = input("Enter CVV: ").strip()



                if payment_method == 2:
                    print("\nDebit Card Payment:")
                    credit_card_number = input("Enter your card number: ").strip()
                    credit_exp = input("Enter Card Expiry Date (MM/YY or MM/YYYY): ").strip()
                    cvv = input("Enter CVV: ").strip()
                    name_on_card = input("Enter Name on Card: ").upper().strip()

                    # Validation loop for card details
                    while True:
                        try:
                            # Validate card number
                            if not (credit_card_number.isdigit() and (len(credit_card_number) == 16)):
                                raise ValueError("\nCard number must be 16 digits.\n")

                            # Validate expiry date format
                            if "/" not in credit_exp:
                                raise ValueError("\nExpiry date must be in MM/YY or MM/YYYY format.\n")
                            
                            parts = credit_exp.split("/")
                            if len(parts) != 2:
                                raise ValueError("\nExpiry date must be in MM/YY or MM/YYYY format.\n")
                            
                            month = int(parts[0])
                            if month < 1 or month > 12:
                                raise ValueError("\nMonth must be between 01 and 12.\n")
                            
                            # Accept either 2-digit or 4-digit year
                            if len(parts[1]) == 2:
                                year = 2000 + int(parts[1])
                            elif len(parts[1]) == 4:
                                year = int(parts[1])
                            else:
                                raise ValueError("\nYear must be in YY or YYYY format.\n")
                            
                            # Compare with today's month and year
                            if year < today.year or (year == today.year and month < today.month):
                                raise ValueError("\nThe card has expired.\n")
                            
                            # Validate CVV
                            if not (cvv.isdigit() and len(cvv) == 3):
                                raise ValueError("\nCVV must be exactly 3 digits.\n")

                            # Validate Card Holder's Name: allow spaces and alphabets only
                            if not all(word.isalpha() for word in name_on_card.split()):
                                raise ValueError("\nName on Card must contain alphabets only and spaces between names.\n")
                            # If all validations pass:
                            print("\nCard details accepted.\n")
                            print("\nPayment Done Successfully.\n")
                            try:
                                self.Total_Fare = float(self.Ticket_Fare)*self.total_seat_booked
                                print("Total Fare: ",self.Total_Fare)
                            except ValueError:
                                self.Total_Fare = 0.0
                                print("\n Not Converting")
                            break  # Exit the inner loop

                        except ValueError as ve:
                            print("\nInvalid Card Details:", ve,"\n")
                            # Prompt the user to re-enter the details
                            credit_card_number = input("Enter your card number: ").strip()
                            credit_exp = input("Enter Card Expiry Date (MM/YY or MM/YYYY): ").strip()
                            cvv = input("Enter CVV: ").strip()
                            name_on_card = input("Enter Name on Card: ").upper().strip()
                break  # Exit the outer loop once payment details are accepted
            except:
                print("\nInvalid input. Please enter a number (1/2).")


        try:
            def generate_unique_pnr():
                while True:
                    pnr = str(random.randint(1000000000, 9999999999))
                    try:
                        with open("PassengerDetails.txt", "r", encoding="utf-8") as f:
                            if pnr in f.read():
                                continue
                    except FileNotFoundError:
                        pass
                    return pnr

            pnr = generate_unique_pnr()
            print(pnr,"(Please remeber this PNR code of your ticket, Thank You!).")
            with open("PassengerDetails.txt", "a",encoding="utf-8") as pd:
                pd.write("Booking Details:\n")
                pd.write("PNR: " + pnr + "\n")
                pd.write(self.From+"\n")
                pd.write(self.To+"\n")
                pd.write(self.Train_Name+"\n")
                pd.write(self.Train_ID+"\n")
                pd.write(self.Arrival_Time+"\n")
                pd.write(self.Departure_Time+"\n")
                for idx, passenger in enumerate(self.passenger_list):
                    pd.write("\nPassenger " + str(idx + 1) + " Details:\n")
                    pd.write("Name: " + passenger["Name"] + "\n")
                    pd.write("Gender: " + passenger["Gender"] + "\n")
                    pd.write("Age: " + str(passenger["Age"]) + "\n")
                    pd.write("Contact No.: +91 " + self.Mobile_no + "\n")
                    
                    pd.write(f"Date: {self.day}\n")
                booked_seats_str = ", ".join(self.booked_by_passenger)
                pd.write(f"\nBooked Seats: {booked_seats_str}\n")
                pd.write(f"\nTotal Fare: ₹{self.Total_Fare}")
                pd.write("\n"+"-" * 40 + "\n")
        except:
            print("\nError writing to file. Please try again.\n")
        


    def showTicket(self):
        """
        Prompts the user for a 10-digit PNR and displays only the corresponding booking details
        (from 'PNR:' to 'Total Fare: ...') from PassengerDetails.txt in a formatted ticket layout.
        If the ticket has been cancelled, it displays an appropriate cancellation message.
        If no ticket is found for the provided PNR, it informs the user accordingly.
        """
        pnr_input = input("Enter your 10-digit PNR number: ").strip()
        found = False
        try:
            with open("PassengerDetails.txt", "r", encoding="utf-8") as file:
                content = file.read()
            # Split the file into booking blocks using the separator line.
            bookings = content.split("-" * 40)
            ticket_block = None
            for booking in bookings:
                if "PNR: " + pnr_input in booking:
                    ticket_block = booking.strip()
                    found = True
                    break
            if not found or not ticket_block:
                print("No ticket found for the provided PNR number.")
                return

            # If the ticket block indicates cancellation, display a cancellation message.
            if "Ticket Cancelled" in ticket_block:
                print("This ticket has been cancelled.")
                return

            # Parse ticket_block into key details.
            lines = ticket_block.splitlines()
            details = {}
            passenger_details = []
            current_passenger = {}
            for line in lines:
                line = line.strip()
                if line.startswith("PNR:"):
                    details["PNR"] = line.split("PNR:")[1].strip()
                elif line.startswith("FROM:"):
                    details["FROM"] = line.split("FROM:")[1].strip()
                elif line.startswith("TO:"):
                    details["TO"] = line.split("TO:")[1].strip()
                elif line.startswith("TRAIN NAME:"):
                    details["TRAIN NAME"] = line.split("TRAIN NAME:")[1].strip()
                elif line.startswith("TRAIN ID:"):
                    details["TRAIN ID"] = line.split("TRAIN ID:")[1].strip()
                elif line.startswith("ARRIVAL TIME:"):
                    details["ARRIVAL TIME"] = line.split("ARRIVAL TIME:")[1].strip()
                elif line.startswith("DEPARTURE TIME:"):
                    details["DEPARTURE TIME"] = line.split("DEPARTURE TIME:")[1].strip()
                elif line.startswith("Passenger"):
                    if current_passenger:
                        passenger_details.append(current_passenger)
                        current_passenger = {}
                elif line.startswith("Name:"):
                    current_passenger["Name"] = line.split("Name:")[1].strip()
                elif line.startswith("Gender:"):
                    current_passenger["Gender"] = line.split("Gender:")[1].strip()
                elif line.startswith("Age:"):
                    current_passenger["Age"] = line.split("Age:")[1].strip()
                elif line.startswith("Contact No.:"):
                    current_passenger["Contact No"] = line.split("Contact No.:")[1].strip()
                elif line.startswith("Date:"):
                    current_passenger["Date"] = line.split("Date:")[1].strip()
                elif line.startswith("Booked Seats:"):
                    details["Booked Seats"] = line.split("Booked Seats:")[1].strip()
                elif line.startswith("Total Fare:"):
                    details["Total Fare"] = line.split("Total Fare:")[1].strip()
            if current_passenger:
                passenger_details.append(current_passenger)

            # Build the formatted ticket.
            ticket_width = 70
            border = "*" * ticket_width
            header = "RAIL EXPRESS TICKET".center(ticket_width)
            ticket_str = border + "\n" + header + "\n" + border + "\n\n"

            # Print FROM and TO on one line.
            from_to_line = f"FROM: {details.get('FROM',''):<20}            TO: {details.get('TO',''):<20}"
            ticket_str += from_to_line + "\n\n"

            # Train details line.
            train_line = f"TRAIN NAME: {details.get('TRAIN NAME',''):<20}      TRAIN ID: {details.get('TRAIN ID',''):<10}"
            ticket_str += train_line + "\n\n"

            # Departure and Arrival.
            dep_arr_line = f"DEPARTURE: {details.get('DEPARTURE TIME',''):<15}            ARRIVAL: {details.get('ARRIVAL TIME',''):<15}"
            ticket_str += dep_arr_line + "\n\n"

            ticket_str += border + "\n\n"
            # Passenger details.
            for idx, p in enumerate(passenger_details, start=1):
                ticket_str += f"Passenger {idx}:\n"
                ticket_str += f"   Name: {p.get('Name','')}, Gender: {p.get('Gender','')}, Age: {p.get('Age','')}, Date: {p.get('Date','')}\n\n"

            ticket_str += border + "\n\n"
            ticket_str += f"Booked Seats: {details.get('Booked Seats','')}\n"
            ticket_str += f"Total Fare: {details.get('Total Fare','')}\n\n"
            ticket_str += "                           HAPPY JOURNEY!                   \n"
            ticket_str += border + "\n"

            print(ticket_str)
        except:
            print("Error reading ticket file.")


    def cancelBooking(self):
        """
        Allows the user to cancel a specific passenger's booking from a ticket.
        The user enters their PNR, then the booking details are displayed.
        The user is then prompted to enter the passenger number to cancel.
        A refund of 30% of that passenger's fare is provided,
        the passenger's details and corresponding seat are removed,
        the total fare is updated (using the same 30% refund logic),
        and the changes are saved back to PassengerDetails.txt.
        Additionally, the cancelled seat is removed from BookedSeats.txt.
        If all passengers are cancelled, the booking is marked as cancelled,
        and the Total Fare line is updated according to the 30% refund logic.
        """
        pnr_input = input("Enter your 10-digit PNR number for cancellation: ").strip()
        found = False
        try:
            with open("PassengerDetails.txt", "r", encoding="utf-8") as file:
                content = file.read()
            # Split into booking blocks
            bookings = content.split("-" * 40)
            booking_index = -1
            for i, booking in enumerate(bookings):
                if "PNR: " + pnr_input in booking:
                    booking_block = booking.strip()
                    booking_index = i
                    found = True
                    break
            if not found:
                print("No booking found for the provided PNR.")
                return

            # Parse the booking block
            lines = booking_block.splitlines()
            details = {}
            passenger_details = []
            current_passenger = {}
            for line in lines:
                line = line.strip()
                if line.startswith("PNR:"):
                    details["PNR"] = line.split("PNR:")[1].strip()
                elif line.startswith("FROM:"):
                    details["FROM"] = line.split("FROM:")[1].strip()
                elif line.startswith("TO:"):
                    details["TO"] = line.split("TO:")[1].strip()
                elif line.startswith("TRAIN NAME:"):
                    details["TRAIN NAME"] = line.split("TRAIN NAME:")[1].strip()
                elif line.startswith("TRAIN ID:"):
                    details["TRAIN ID"] = line.split("TRAIN ID:")[1].strip()
                elif line.startswith("ARRIVAL TIME:"):
                    details["ARRIVAL TIME"] = line.split("ARRIVAL TIME:")[1].strip()
                elif line.startswith("DEPARTURE TIME:"):
                    details["DEPARTURE TIME"] = line.split("DEPARTURE TIME:")[1].strip()
                elif line.startswith("Passenger"):
                    if current_passenger:
                        passenger_details.append(current_passenger)
                        current_passenger = {}
                elif line.startswith("Name:"):
                    current_passenger["Name"] = line.split("Name:")[1].strip()
                elif line.startswith("Gender:"):
                    current_passenger["Gender"] = line.split("Gender:")[1].strip()
                elif line.startswith("Age:"):
                    current_passenger["Age"] = line.split("Age:")[1].strip()
                elif line.startswith("Contact No.:"):
                    current_passenger["Contact No"] = line.split("Contact No.:")[1].strip()
                elif line.startswith("Date:"):
                    current_passenger["Date"] = line.split("Date:")[1].strip()
                elif line.startswith("Booked Seats:"):
                    details["Booked Seats"] = line.split("Booked Seats:")[1].strip()
                elif line.startswith("Total Fare:"):
                    tf = line.split("Total Fare:")[1].strip().lstrip("₹")
                    details["Total Fare"] = tf
            if current_passenger:
                passenger_details.append(current_passenger)
            
            # If booking is already marked as cancelled, report it.
            if "Ticket Cancelled" in booking_block:
                print("Ticket is already cancelled for this PNR.")
                return

            # Display current booking summary
            print("\nCurrent Booking Details:")
            print("PNR:", details.get("PNR", ""))
            print("Passengers:")
            for idx, p in enumerate(passenger_details, start=1):
                print(f"  {idx}. Name: {p.get('Name','')}, Date: {p.get('Date','')}")
            print("Booked Seats:", details.get("Booked Seats", ""))
            print("Total Fare: ₹" + details.get("Total Fare", "0"))
            print()

            # Prompt for passenger number to cancel
            cancel_input = input("Enter the passenger number to cancel: ").strip()
            try:
                cancel_index = int(cancel_input) - 1
                if cancel_index < 0 or cancel_index >= len(passenger_details):
                    print("Invalid passenger number.")
                    return
            except:
                print("Invalid input for passenger number.")
                return

            # Calculate fare per passenger (original full fare)
            try:
                total_fare_val = float(details["Total Fare"])
            except:
                print("Error in total fare value.")
                return
            original_passenger_count = len(passenger_details)
            fare_per_passenger = total_fare_val / original_passenger_count

            # Refund: 30% of cancelled ticket's fare
            refund_amount = fare_per_passenger * 0.30

            # New total fare calculation:
            # For the cancelled passenger, 70% of fare is retained.
            # For remaining passengers, full fare is charged.
            if original_passenger_count > 0:
                new_total_fare = fare_per_passenger * ((original_passenger_count - 1) + 0.70)
            else:
                new_total_fare = 0.0

            print(f"\nCancellation successful for Passenger {cancel_index+1}.")
            print(f"Refund Amount: ₹{refund_amount:.2f}")

            # Remove the cancelled passenger
            cancelled_passenger = passenger_details.pop(cancel_index)

            # Update Booked Seats: remove the seat corresponding to the cancelled passenger.
            booked_seats_list = [seat.strip() for seat in details.get("Booked Seats", "").split(",") if seat.strip()]
            if cancel_index < len(booked_seats_list):
                cancelled_seat = booked_seats_list.pop(cancel_index)
            else:
                cancelled_seat = ""
            
            # Update the booking block
            if len(passenger_details) == 0:
                # If all passengers are cancelled, we still update Total Fare with computed value.
                new_block = "Booking Details:\n"
                new_block += "PNR: " + details.get("PNR", "") + "\n"
                new_block += "Ticket Cancelled\n"
                new_block += "Total Fare: ₹" + str(new_total_fare) + "\n"
            else:
                details["Booked Seats"] = ", ".join(booked_seats_list)
                details["Total Fare"] = str(new_total_fare)
                new_block = ""
                new_block += "Booking Details:\n"
                new_block += "PNR: " + details.get("PNR", "") + "\n"
                new_block += "FROM: " + details.get("FROM", "") + "\n"
                new_block += "TO: " + details.get("TO", "") + "\n"
                new_block += "TRAIN NAME: " + details.get("TRAIN NAME", "") + "\n"
                new_block += "TRAIN ID: " + details.get("TRAIN ID", "") + "\n"
                new_block += "ARRIVAL TIME: " + details.get("ARRIVAL TIME", "") + "\n"
                new_block += "DEPARTURE TIME: " + details.get("DEPARTURE TIME", "") + "\n\n"
                for i, p in enumerate(passenger_details, start=1):
                    new_block += f"Passenger {i} Details:\n"
                    new_block += "Name: " + p.get("Name", "") + "\n"
                    new_block += "Gender: " + p.get("Gender", "") + "\n"
                    new_block += "Age: " + p.get("Age", "") + "\n"
                    new_block += "Contact No.: " + p.get("Contact No", "") + "\n"
                    new_block += "Date: " + p.get("Date", "") + "\n\n"
                new_block += "Booked Seats: " + details.get("Booked Seats", "") + "\n"
                new_block += "Total Fare: ₹" + details.get("Total Fare", "") + "\n"

            # Update the bookings list with the new block.
            bookings[booking_index] = new_block

            # Reassemble the full file content.
            updated_content = ("\n" + "-" * 40 + "\n").join(b.strip() for b in bookings if b.strip())
            with open("PassengerDetails.txt", "w", encoding="utf-8") as file:
                file.write(updated_content + "\n" + "-" * 40 + "\n")
            print("\nBooking record updated successfully.")

            # Now update BookedSeats.txt to unbook the cancelled seat.
            # Format in BookedSeats.txt is: "Date,Seat"
            if cancelled_seat:
                try:
                    with open("BookedSeats.txt", "r", encoding="utf-8") as bs_file:
                        bs_lines = bs_file.readlines()
                    updated_bs_lines = []
                    # We assume all passengers share the same booking date.
                    booking_date = passenger_details[0]["Date"] if passenger_details else cancelled_passenger.get("Date", "")
                    for line in bs_lines:
                        if booking_date and line.strip() == f"{booking_date},{cancelled_seat}":
                            continue  # Skip this line to unbook the seat.
                        else:
                            updated_bs_lines.append(line)
                    with open("BookedSeats.txt", "w", encoding="utf-8") as bs_file:
                        bs_file.writelines(updated_bs_lines)
                    print(f"Seat {cancelled_seat} on {booking_date} is now unbooked.")
                except:
                    print("Error updating BookedSeats.txt.")
        except:
            print("\nError during cancellation process.")
            
    
confirm = True
EH1 = True

RE = RailExpress()
while EH1:
    try:
        while confirm:
            choice = int(input("\nPress 1: RailExpress Login\nPress 2: Ticket Booking\nPress 3: Show a Ticket\nPress 4: Cancel Booking\nPress 5: Exit the System.\n"))

            if (choice == 1):
                RE.signUp()
                continue
            elif (choice == 2):
                RE.ticketBooking()
                continue
            elif (choice == 3):
                RE.showTicket()
                continue
            elif (choice == 4):
                RE.cancelBooking()
                continue
            elif (choice == 5):
                break
            else:
                print("\nInvalid choice. Please choose a valid option.")
        break
    except:
        print("\nInvalid input. Please enter a number.")
