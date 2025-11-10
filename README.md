# WWT Microservices Test Task

### Description
This simple microservices application contains the following features:
- Register users and save them to the database
- Authenticate the user with JWT
- Process a text message: reverse it, convert to uppercase, save a log to the database
### Build
- Create `.env` file in project root with following keys:

```properties
JWT_SECRET=YOUR_SECRET_LONGER_THAN_32_SYMBOLS
INTERNAL_TOKEN=YOUR_INTERNAL_TOKEN

POSTGRES_USER=YOUR_USER
POSTGRES_PASSWORD=YOUR_PASSWORD
POSTGRES_DB=DB_NAME
POSTGRES_PORT=YOUR_PORT
```
- From root folder run
- 
```
docker-compose up --build
```

By default, the application is available at the following address:

- auth-api

```
http://localhost:8080
```

- data-api
 
```
http://localhost:8081
```

### Register

```
POST http://localhost:8080/api/auth/register
```

with body:

```json
{
    "email": "test@example.com",
    "password": "somePassword"
}
```

Or using curl:

```bash
curl -X POST http://localhost:8080/api/auth/register \
-H "Content-Type: application/json" \
-d '{
  "email": "test@example.com",
  "password": "somePassword"
}'
```

Response: HTTP `201 Created`

### Login

```
POST http://localhost:8080/api/auth/login
```

with body:

```json
{
    "email": "testl@example.com",
    "password": "somePassword"
}
```

Or using curl:

```bash
curl -X POST http://localhost:8080/api/auth/login \
-H "Content-Type: application/json" \
-d '{
  "email": "test@example.com",
  "password": "somePassword"
}'
```

Response example: HTTP `200 OK`

```json
{
    "token": "YOUR_TOKEN"
}
```

### Process text
```

POST http://localhost:8080/api/process
```

with body:

```json
{
    "text": "some_text_to_process"
}
```

Use your token from `/login` request, authentication type: Bearer


Or using curl:

```bash
curl -X POST http://localhost:8080/api/process \
-H "Content-Type: application/json" \
-H "Authorization: Bearer YOUR_TOKEN" \
-d '{
  "text": "some_text_to_process"
}'
```

Response example: HTTP `200 OK`

```json
{
    "text": "SSECORP_OT_TXET_EMOS"
}
```