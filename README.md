To start MySQL in the docker, open a new terminal and run the following command:

```shell
docker run --name {mysql} -e MYSQL_ROOT_PASSWORD=1234 -p 3306:3306 mysql
```

To start the frontend, open a new terminal and run the following command:

```shell
cd frontend
npm npx create-react-app frontend --template typescript
npm run start
```

To start axios and MUI

```shell
MUI: npm install @mui/material @emotion/react @emotion/styled
Axios: npm i axios
```