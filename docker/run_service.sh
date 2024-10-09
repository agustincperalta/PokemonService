#!/bin/bash

environment=$ENVIRONMENT
clusternumber=$CLUSTER_NUMBER
configbranch=$CONFIG_BRANCH

application=node.customer360-service
fileName=run_service
configServicePort=8765



exec "$@"
