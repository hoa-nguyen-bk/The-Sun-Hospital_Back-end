## Step to setup
Create a container in Docker

```cmd
docker run --name the-sun-hospital -p 3308:3306 -e MYSQL_ROOT_PASSWORD=admin123 -d mysql
```

Connect to DBeaver
```
Server Host: localhost
Port: 3308
Database: (empty, do not fill anything here)
Username: root
Password: admin123
Driver Properties: 
    allowPublicKeyRetrieval: true
```
![alt text](image_read_me/image.png)

You can set name to The sun hospital


Check for the diagram in Bbeaver, it should look like this

![alt text](image_read_me/image2.png)
Connect database in Inteligi
![img.png](image_read_me/img.png)

Postman public link:
https://www.postman.com/martian-station-399661/workspace/the-sun-hospital/request/38306441-eba1b928-9f0e-4326-b3db-e69304678cc1?action=share&creator=38306441&ctx=documentation&active-environment=38306441-33c9f007-6ffd-4f3d-a925-f81bb93cb5fb