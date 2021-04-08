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



functions / services 
	
	authentication-service - akalanka
	product-management-service - kasun
	payment-management-service - akalanka
	research-management-service - eshan	user-management-service - kanchila	funding-service - sasitha


databases (* all characters should be lowercase and use `_` for spaces *)

~~~~~~~~~~~~~~~~~~~~~~~~~~~
DB user name - paf_user
DB password - ^paf_user_pw_000
~~~~~~~~~~~~~~~~~~~~~~~~~~~

	paf_product_management - DB
		products(products_id (PK INT AI) , name , description , quantity , price , added_date )
		products_sold(products_id (FK PK) , buyer_id , sold_date )

	paf_payment_management - DB
		shopping_cart( user_id (PK) , product_id , quantity )

	paf_research_management - DB
		researchs(research_id (PK) , topic , estimated_cost , details , published_date )
			
	paf_user_management - DB
		users(user_id(PK AI) , username , password , user_level ,email , fname , lname , dob , address , tp_number) user levels {10-admin,20-buyers,30-funding bodies,40-researchers }
		
	paf_funding_management- DB
		received_funds(fund_id (PK) , research_id (FK) , finding_body_id , amount )

	paf_authentication_management - memory DB
		logged_in_users(user_id (PK) , timestap , user_level) user levels {10-admin,20-buyers,30-funding bodies,40-researchers }


	
























