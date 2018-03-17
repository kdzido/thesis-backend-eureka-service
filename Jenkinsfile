pipeline {
    agent { node { label 'docker' } }

    environment {
        PIPELINE_BUILD_ID = "${BUILD_TAG}"
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
                echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"

                sh './gradlew clean build'
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
