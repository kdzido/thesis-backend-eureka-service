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
        withEnv(["COMPOSE_FILE=docker-compose-test-local.yml"]) {
            stage('Unit') {
                steps {
                    sh 'docker-compose run --rm unit'
//                    sh 'docker-compose build app'
                }
            }
        }
    }

}
