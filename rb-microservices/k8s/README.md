Kullandığımız Kubernetes komutları
--------------------

- Örnek uygulamayı (rb-k8s-helloworld) koşturmak için; **(korayguney geçen yerleri kendi Docker Hub hesap adınız ile değiştirmeyi unutmayınız!)**
  - docker build -t  korayguney/helloworld-rb .
  - docker push korayguney/helloworld-rb
  - docker run --rm -it -p 8080:8080 korayguney/helloworld-rb
  - kubectl run hello-world --image=korayguney/helloworld-rb --port=80
  - kubectl get pods
  - kubectl get pod hello-world -w _(canlı takip etmek için)_
  - kubectl port-forward pod/hello-world 8080:8080
  - kubectl delete pod hello-world
  -   kubectl get pods
- Örnek uygulamamızı (rb-k8s-helloworld)  manuel scale etmek için;
  - kubectl apply -f hello-world-deploy.yml
  - kubectl get pods
  - kubectl scale --replicas=5 deployment/hello-world
  - kubectl get pods -w
  - kubectl scale --replicas=3 deployment/hello-world
  - kubectl get pods -w

- Postgres DB yi pod olarak deploy etmek için;
  - kubectl apply -f bootstrap/postgres
  - kubectl logs postgres-0

- Postgres üzerinde DB oluşturma;
  - kubectl exec -it postgres-0 -- psql -U robotdreams -d postgres
  - create database validation;
  - create database insurance;
  - create database notification;

- ZIPKIN deploy;
  - kubectl apply -f .\bootstrap\zipkin
  - kubectl logs zipkin-0
  - minikube tunnel _(url den erişmek için)_

- RabbitMQ deploy;
  - kubectl apply -f .\bootstrap\rabbitmq
  - kubectl logs rabbitmq-0
  - kubectl port-forward service/rabbitmq 31672:15672

- Servicelerimizi deploy etmek;
  - kubectl apply -f service\insurance
  - kubectl apply -f service\notification
  - kubectl apply -f service\validation


