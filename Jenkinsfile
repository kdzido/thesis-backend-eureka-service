pipeline {
    agent { node { label 'docker' } }

    environment {
        PIPELINE_BUILD_ID = "${BUILD_TAG}"
        DOCKERHUB_USER = credentials("dockerhub")
        DOCKERHUB_PASSWORD = credentials("dockerhub")
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

                echo "dockerhub user: ${env.DOCKERHUB_USER}"
                echo "dockerhub pass: ${env.DOCKERHUB_PASSWORD}"

                sh './gradlew clean build buildDockerImage'



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
