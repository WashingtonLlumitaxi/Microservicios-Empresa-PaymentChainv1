Case study


PaymentChain, a startup in the financial sector needs to create a technological solution that provides 
support to the core of its business. Manage your customer information, products, transactions, communications, 
communication with business partners and other operational processes


<--- Organization Model --->

![image](https://github.com/user-attachments/assets/244c2617-2742-48b5-a2a4-4a334ea0d8ea)

It is dividel into two parts: the business model and the infrastructure manager.


<--- Bussines database model --->

![image](https://github.com/user-attachments/assets/2330dcb8-dfde-4400-bde8-f0a91740ff9c)

<--- Configuration Ports --->

-- Module Businessdomain
- Produc = 8083
- Customer = 8081   || Local = 8081  || development = 49152  || production = 48152
- Transaction = 8082

-- Module Infrastructure
- ConfigServer = 8888
- EurekaServer = 8761
- SpringBootAdmin = 8762

<--- Model External Configuration --->

![image](https://github.com/user-attachments/assets/cb842895-d96f-4f72-a9c0-a492874a4438)


<-- Note  -->

Is important configure the environment variables for the perfect functioning the confing-server

-cmd
- set REPOSITORY_PATH=C:/Users/DELL/Desktop/Microservicios/paymentchainparent/config-server-repo 
- echo %REPOSITORY_PATH%  

<--- Spring Cloud Gateway --->

![image](https://github.com/user-attachments/assets/733eeaf6-c2c9-410a-bfeb-6de53a124e5c)

- Implementation ApiGateway
- Creation to package setups (Class: GloblalPostFilter and GlobalPreFilter)
- 
