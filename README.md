# URL Shortner Application 
 This is an rest api used to take long url and generate a small alternative url.
## Tekstac Used
- Spring Boot
- Redis (Data caching)
## Instruction to user
To use url-shortener clone this application to local machine. Make sure your local machine must have Java version >1.8, maven, redis pre installed.
After cloning run the application and redis server. You can use Postman to check the api create a Post request .
{
 "url": <url>
}
In the response you will get a small url copy paste it in the browser it will navigate to the respective page.
