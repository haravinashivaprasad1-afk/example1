pipeline {
    agent any

    triggers {
        cron('H/5 * * * *')
    }

    environment {
        DOCKER_HUB_USER = 'haravinashivaprasad1'
        IMAGE_NAME = 'devops-cie2-q4'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Artifact') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t $DOCKER_HUB_USER/$IMAGE_NAME:latest .'
            }
        }

        stage('Docker Push') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'docker-hub-credentials',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                    sh 'docker push $DOCKER_HUB_USER/$IMAGE_NAME:latest'
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                sh """
                    if command -v kubectl &> /dev/null; then
                        KUBECTL="kubectl"
                    elif command -v minikube &> /dev/null; then
                        KUBECTL="minikube kubectl --"
                    else
                        echo "ERROR: Neither kubectl nor minikube found"
                        exit 1
                    fi
                    \$KUBECTL apply --validate=false -f k8s-deployment.yaml
                    \$KUBECTL rollout restart deployment/devops-cie2-q4
                """
            }
        }
    }
}
