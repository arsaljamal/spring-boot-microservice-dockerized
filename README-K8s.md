== Deploy to Kubernetes using Standalone Manifests

**Code**

. Change to `manifests/standalone` directory
. Show and explain manifests:

	more yourservice-deployment.yaml
	more yourservice-service.yaml

. Create deploymnent:

	kubectl create -f yourservice-deployment.yaml

. Show status:

	kubectl get deployment
	kubectl get pods
	kubectl logs <pod-name>

. Create service:

	kubectl create -f yourservice-service.yaml

. Show status:

	kubectl get svc

. Access the application:

	curl http://localhost:8080/

. Delete service and deployment:

	kubectl delete -f yourservice-service.yaml
	kubectl delete -f yourservice-deployment.yaml

== Deploy to Kubernetes using Standalone Single Manifest

**Code**

. Show and explain combined manifest:

	more yourservice.yaml

. Deploy application to Kubernetes using a single manifest:

	kubectl create -f yourservice.yaml

. Check deployment, pods and service:

	kubectl get svc,deployment,pods

. Access the application:

	curl http://localhost:8080/

. Delete deployment and service (a different way to delete):

	kubectl delete deployment/yourservice svc/yourservice

== Introduction to Helm Charts

**Helm**

. Explain what is Helm chart?
. Key concepts - client, tiller, charts
. Sample Helm chart

== Deploy Application to Kubernetes using Helm Charts

**Code**

. Install the Helm CLI:

	brew install kubernetes-helm
+
If Helm CLI is already installed then use `brew upgrade kubernetes-helm`.
+
. Check Helm version:

	helm version

. Install Helm in Kubernetes cluster:
+
helm init
+
If Helm has already been initialized on the cluster, then you may have to upgrade Tiller:
+
helm init --upgrade
+
. Show tiller is running:

	kubectl get pods -n kube-system

. Change to `manifests/charts` directory
. Install the Helm chart:

	helm install --name myapp myapp

. Check that the resources are running:

	kubectl get svc,deployment,pods

. Access the application:

	curl http://$(kubectl get svc/yourservice \
        -o jsonpath='{.status.loadBalancer.ingress[0].hostname}'):8080/

. Delete the Helm chart:

	helm delete --purge myapp

. Reset helm:

	helm reset --force