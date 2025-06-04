import psycopg2 as psy
class Connection :
    def create_connection():
        try:
            connection = psy.connect(
                host="localhost",
                database="mmt ",
                user="postgres",
                password="sk1009"
            )
            return connection
        except Exception as e:
            print(f"Error connecting to database: {e}")
            return None        
