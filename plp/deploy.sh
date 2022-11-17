version=$1
echo " ** Realizando deploy del servicio  version: $version ** "
echo " Paso 1 : docker build => docker build -f Dockerfile_calidad -t pic/credit:$version "
docker build -f Dockerfile -t pic/credit:$version ../../
sleep 1
echo " Paso 2 : docker tag   => docker tag pic/credit:$version 180.183.170.62:4443/credit:$version"
docker tag pic/credit:$version 180.183.170.62:4443/pic/credit:$version
sleep 1
echo " Paso 3 : docker push  => docker push 180.183.170.62:4443/credit:$version "
docker push 180.183.170.62:4443/pic/credit:$version
sleep 1
echo " Paso 4 : docker delete "
kubectl delete -f credit-deployment.yaml 
sleep 2
echo " Paso 5 : docker create "
kubectl create -f credit-deployment.yaml 
echo " ==> Finalizando deploy del servicio  **  "
