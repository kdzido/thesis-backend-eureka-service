pipeline {
    agent { node { label 'docker' } }

    environment {
        PIPELINE_BUILD_ID = "${BUILD_TAG}"
        DOCKERHUB_CREDS = credentials("dockerhub")
        // implicit DOCKERHUB_CREDS_USR
        // implicit DOCKERHUB_CREDS_PSW
    }

    stages {
        stage('Commit Stage') {
            steps {
                echo "BUILD_NUMBER: ${env.BUILD_NUMBER}"
                echo "BUILD_ID: ${env.BUILD_ID}"
                echo "NODE_NAME: ${env.NODE_NAME}"
                echo "JOB_NAME: ${env.JOB_NAME}"
                echo "BUILD_TAG: ${env.BUILD_TAG}"
                echo "JENKINS_URL: ${env.JENKINS_URL}"
                echo "PIPELINE_BUILD_ID: ${env.PIPELINE_BUILD_ID}"
                echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"
                echo "Artifactory URI ${env.ARTIFACTORY_URI}"

                echo "dockerhub creds: ${env.DOCKERHUB_CREDS}"
                echo "dockerhub user: ${env.DOCKERHUB_CREDS_USR}"
                echo "dockerhub pass: ${env.DOCKERHUB_CREDS_PSW}"

                sh './gradlew clean build buildDockerImage pushDockerImage'



            }
        }
        stage('Acceptance Stage') {
            steps {
//                input "Proceed?"
                echo 'TODO Acceptance Stage'
            }
        }
    }

//    post {
//        always {
//             junit 'build/reports/**/*.xml'
//        }
//    }
}
