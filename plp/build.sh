#!/bin/sh
image=$1
docker build -f Dockerfile -t pic/credit:$image ../../
docker tag pic/credit:$image 180.183.170.62:4443/credit:$image
docker push 180.183.170.62:4443/credit:$image
if [[ $? == 0 ]]
then
	echo ""
	echo "Se creo satisfactoria la siguiente imagen"
	echo ""
	echo "172.27.2.25:4443/credit:"$image
  kubectl delete -f credit-deployment.yaml 
  sleep 2
  if [[ $? == 0 ]]
  then
  	echo ""
  	echo "Se elimino correctamnente el pod"
  	echo ""
    echo " Paso 5 : docker create "
    kubectl create -f credit-deployment.yaml 
    if [[ $? == 0 ]]
    then
    	echo ""
    	echo "Se creo correctamente version de pod $1"
    	echo ""
      echo " ==> Finalizando deploy del servicio  **  "
    else
    	echo ""
    	echo "POR FAVOR REVISAR create"	
    fi
  else
  	echo ""
  	echo "POR FAVOR REVISAR delete"	
  fi
else
	echo ""
	echo "POR FAVOR REVISAR"	
fi