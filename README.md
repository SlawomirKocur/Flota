# Flota
Application which supports optimization of decision making process in order to maximize profit

User can add a new vessel , new type of cargo and new port to the data base
User can view details about owned vessels, types of cargos and details about ports from the database

Most important feature of this app: User can set details of new contract of cargo transportation. 
App wil analyse those details taking into consideration informations stored in database.
Finally app will suggest which vessel should take the contract in order to maximize profit. 


Self- assessment of this project:

The application meets all requirements set at the begining of the project: analyze new contact,
                                                                           suggest solution,
                                                                           can add new vessels, ports, cargo types,
                                                                           can view the details from database.

On the other hand during building the app an issue has come alive and has not beeen solved.
It is not possible to use selected values (from JComboBox) in other classess. Always when it was tried a null value appeard.
So other variables had null value. Only when values form(JComboBOX)were used in same class it was possible to use them for other operations.
That issue made some classes very big (almost 1000 lines of code) and hard to worh with. 
