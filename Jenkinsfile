node {

    stage("Main build") {

        checkout scm

        docker.image('openjdk:8-jdk').inside {

            stage ('Test') {
                steps {
                    sh "./gradlew clean test"
                }
            }

            stage ('Build') {
                steps {
                    sh "./gradlew clean build"
                }
            }
        }
    }

}
