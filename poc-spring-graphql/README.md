# poc-spring-graphql

    http://localhost:8080/h2/login.jsp 

    http://localhost:8080/swagger-ui/

    http://localhost:8080/actuator  

    http://localhost:8080/playground 

    http://localhost:8080/voyager

### Examples

#### Query:
    {
    getClientByDocument(document: "456") {
            name
            birthDate
            id
        }
    }

    {
        getAllClient {
            name
            birthDate
            id
        }
    }

#### Mutation:
    mutation {
        createClient(
            client: {
                name: "joão"
                document: "11111"
                birthDate: "2021-09-26T18:23:09.859109546"
                addressCharge: {
                    address: "X"
                    number: 10
                    district: "district"
                    city: "city"
                    federativeUnit: "MG"
                }
            }
        ) {
            id
        }
    }


### Referência 
https://github.com/profdiovani/mbari

https://www.youtube.com/watch?v=nju6jFW8CVw&list=PLiwhu8iLxKwL1TU0RMM6z7TtkyW-3-5Wi

