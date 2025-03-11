pipeline {
    agent any

    environment {
        IMAGE_NAME = "mon-etablissement"
        IMAGE_TAG = "latest"
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/mami-adame/mon-etablissement.git'
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t ${IMAGE_NAME}:${IMAGE_TAG} .'
            }
        }

        stage('Push Docker Image') {
            steps {
                withDockerRegistry([credentialsId: 'docker-hub-credentials', url: '']) {
                    sh 'docker tag ${IMAGE_NAME}:${IMAGE_TAG} utilisateur/${IMAGE_NAME}:${IMAGE_TAG}'
                    sh 'docker push utilisateur/${IMAGE_NAME}:${IMAGE_TAG}'
                }
            }
        }

        stage('Deploy to Server') {
            steps {
                sshagent(['server-credentials']) {
                    sh '''
                    ssh user@server "docker pull utilisateur/${IMAGE_NAME}:${IMAGE_TAG} &&
                    docker stop ${IMAGE_NAME} || true &&
                    docker rm ${IMAGE_NAME} || true &&
                    docker run -d --name ${IMAGE_NAME} -p 8080:8080 utilisateur/${IMAGE_NAME}:${IMAGE_TAG}"
                    '''
                }
            }
        }
    }

    post {
        success {
            echo "Déploiement réussi ✅"
        }
        failure {
            echo "Erreur lors du pipeline ❌"
        }
    }
}
