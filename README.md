# Injira Identity Provider

## Getting Started

### 1. Prerequisites

- Java 21 or higher
- Docker
## Using Docker:

Download this image from S3 bucket (URL on the in the project description)

Then execute
```bash
docker load --input oauth_idp.tar
```

Then run the container

### 2. Environment Configuration

Before running, you must configure the following environment variables. You can create a `.env` file in the project root to store these.

```bash
LINKEDIN_CLIENT_ID=***
LINKEDIN_CLIENT_SECRET=***
DATABASE_URL=jdbc:postgresql://****
```

### 3. Start the Database

Use Docker Compose to start the PostgreSQL database container.

```bash
docker-compose up -d
```

### 4. Run the Application

Start the Spring Boot authorization server.

```bash
./mvnw spring-boot:run
```

The server will start on `http://localhost:8080`.

## End-to-End Flow Example: Authorization Code Grant

This example demonstrates the complete process of a user authorizing a client application to access their data.

### Step 1: Register a User (Resource Owner)

First, create a user in the system.

```bash
curl -X POST http://localhost:8080/registration/user \
-H "Content-Type: application/json" \
-d '{
    "firstName": "John",
    "lastName": "Doe",
    "password": "password123",
    "email": "john@gmail.com"
}'
```

### Step 2: Register a Client Application

Next, register the application that will request access to the user's data.

```bash
curl -X POST http://localhost:8080/registration/client \
-H "Content-Type: application/json" \
-d '{
    "clientId": "my-client-app",
    "clientSecret": "secret",
    "redirectUris": ["http://127.0.0.1:8080/authorized"],
    "scopes": ["openid", "profile"],
    "grantTypes": ["authorization_code", "refresh_token"]
}'
```

### Step 3: Initiate the Authorization Flow

The client application would redirect the user's browser to this URL.

```
http://localhost:8080/oauth2/authorize?response_type=code&client_id=my-client-app&redirect_uri=http://127.0.0.1:8080/authorized&scope=openid%20profile
```

- `response_type=code`: Specifies the Authorization Code Grant.
- `client_id`: Identifies the client registered in Step 2.
- `redirect_uri`: The location to send the user back to after authorization.
- `scope`: The permissions the client is requesting (`openid` for OIDC, `profile` for user info).

### Step 4: Authenticate and Grant Consent

Your browser will be redirected to the server's login page.

1. Enter the credentials for the user you created: email: `john@gmail.com`, Password: `password123`.
2. After successful login, you will be presented with a consent screen asking if you want to grant "my-client-app" access to your profile.
3. Click "Accept".

### Step 5: Receive the Authorization Code

The server will redirect your browser to the `redirect_uri` with an authorization code appended as a query parameter. The URL will look like this:

```
http://127.0.0.1:8080/authorized?code=xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx
```

**Note**: In a real application, the client would handle this redirect and extract the code. Since we don't have a live client at that URL, your browser will show a 404, but the code in the URL is still valid. Copy this code.

### Step 6: Exchange the Code for Tokens

The client application now takes this code and securely exchanges it for an access token, refresh token, and ID token by making a POST request to the `/oauth2/token` endpoint.

```bash
curl -X POST http://localhost:8080/oauth2/token \
-H "Content-Type: application/x-www-form-urlencoded" \
-u "my-client-app:secret" \
-d "grant_type=authorization_code&code=PASTE_THE_CODE_FROM_PREVIOUS_STEP&redirect_uri=http://127.0.0.1:8080/authorized"
```

- `-u "my-client-app:secret"`: This uses HTTP Basic Authentication to send the client's credentials.

### Step 7: Receive and Use the Tokens

The server will respond with a JSON payload containing the tokens:

```json
{
    "access_token": "eyJhbGciOiJSUzI1NiJ9...",
    "refresh_token": "wFz....",
    "scope": "openid profile",
    "id_token": "eyJhbGciOiJSUzI1NiJ9...",
    "token_type": "Bearer",
    "expires_in": 299
}
```
