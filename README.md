# EventRegistration

docker build -t jstrem/registration-image:latest .

docker run -d --name climb-db -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=climb-db -p 5432:5432 postgres:13

java -jar ./api/target/api-1.0.0-SNAPSHOT.jar


## Azure
// install Azure CLI
// open cmd
// az aks install-cli
az login
az account set --subscription 93e7bea7-07f6-4624-a530-259ee9dadfda
az aks get-credentials --resource-group climbapp_group_1638641310048 --name climbapp
kubectl get nodes
// cd to your kubernetes deployment.yaml (C:/Users/Jana/Documents/fax/mag/1.letnik/RSO/EventRegistration/k8s)
// kubectl create -f event-registration-deployment.yaml
kubectl apply -f event-registration-deployment.yaml --namespace climb
kubectl --namespace climb get services -o wide
kubectl get services
[EXTERNAL-IP]:[PORT]/[REST path] in browser
kubectl get services
kubectl get deployments
kubectl get pods
kubectl logs event-registration-deployment-68744cc4fc-????
kubectl delete pod [image name]
