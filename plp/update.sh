#!/bin/sh
image=$1
kubectl set image deploy credit-service-deploy -n pic credit-service-image=180.183.170.62:4443/credit-service:$1 --record
sleep 2
kubectl rollout status deploy -n pic credit-service-deploy
