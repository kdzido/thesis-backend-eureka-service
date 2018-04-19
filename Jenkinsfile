pipeline {
    agent {
        node { label 'docker-enabled' }
    }

    environment {
        PIPELINE_BUILD_ID = "${GIT_BRANCH}-${BUILD_NUMBER}"
        DOCKERHUB_CREDS = credentials("dockerhub")
        // implicit DOCKERHUB_CREDS_USR
        // implicit DOCKERHUB_CREDS_PSW
    }

    stages {
        stage('Unit') {
            steps {
                withEnv(["COMPOSE_FILE=docker-compose-test-local.yml"]) {
                    sh 'docker-compose run --rm unit'
                    sh 'docker-compose build app'
                }
            }
        }
        stage('Staging') {
            steps {
                withEnv(["COMPOSE_FILE=docker-compose-test-local.yml"]) {
                    sh 'docker-compose up -d eurekapeer1'
                    sh 'docker-compose up -d eurekapeer2'
                    sh 'sleep 60'
                }
            }
        }
    }

    post {
        always {
            withEnv(["COMPOSE_FILE=docker-compose-test-local.yml"]) {
                sh "docker-compose down"
            }
        }
    }

}
