# PAF_Project

stakeholders and their activities 

	researchers 
		Add / Remove /Update / View / Search products details - product management (service) - kasun
		Add / Remove /Update / View / Search Research documents - research management (documents) (service) - eshan

	funding bodies
		Add /Update / View (filter history) Funding. - Funding service (service) - sasitha
		View / Search products details. - product management (service) - kasun
		View / Search Research documents - research management (documents) (service) - eshan
		
	buyers
		View / Search products details. - product management (service) - kasun
		Add / Remove /Update / View Shopping Cart Items - payment methods / management (service) - akalanka

	Admin
		Add / Remove /Update / View Users (Researchers, Buyers, Admin, Funding bodies). - user management (service) - kanchila
		Remove products - product management (service) - kasun
		Remove Research documents - research management (documents) (service) - eshan



functions
	product management (service) - kasun
	payment methods / management (service) - akalanka
	research management (documents) (service) - eshan	user management (service) - kanchila	Funding service (service) - sasitha


databases (* all characters should be lowercase and use `_` for spaces *)

	product management (service) - DB
		product(products_id (PK) , name , description , quantity , price , added_date )
		product_sold(products_id (FK PK) , buyer_id , sold_date )

	payment methods / management (service) - DB
		shopping_cart( cart_id , product_id , quantity )

	research management (documents) (service) - DB
		research(research_id , topic , estimated_cost , details , published_date )
			
	user management (service) - DB
		user(username , password , email , fname , lname , dob , address , tp_number ,type)
		
	Funding service (service) - DB
		received_funds(fund_id (PK) , research_id (FK) , finding_body_id , amount )


	
























