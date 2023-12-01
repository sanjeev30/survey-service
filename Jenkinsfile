@NonCPS
def generateTag() {
    return "build-" + new Date().format("yyyyMMdd-HHmmss")
}

pipeline {
    environment {
        registry = "sanjeev7/hw3"
        registryCredential = 'docker_creds'
    }
    agent any

    tools {
        maven 'Maven' // Replace with the desired Maven version
    }

    stages{

        stage('Build') {
            steps {
                script {
                    // sh 'mvn clean install'
                    // sh 'mvn clean package'
                    // sh 'mvn war:war'
                    checkout scm
                    sh 'mvn clean package'
                    // sh 'rm -rf *.war'
                    // sh 'jar -cvf student_survey-1.war -C src/main/webapp/ .'

               }
            }
        }

        stage('Tagging Image') {
            steps {
                script {
                    // sh 'echo ${BUILD_TIMESTAMP}'
                    tag = generateTag()
                    docker.withRegistry('',registryCredential){
                      def customImage = docker.build("mythprat/swe645hw3:"+tag)
                   }
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                script {
                    sh 'echo ${BUILD_TIMESTAMP}'
                    docker.withRegistry('',registryCredential) {
                        def image = docker.build('mythprat/swe645hw3:'+tag, '.')
                        docker.withRegistry('',registryCredential) {
                            image.push()
                        }
                    }
                }
            }
        }

      stage('Deploying Rancher to single node') {
         steps {
            script{
                // def kubeconfigPath = '/home/ubuntu/.kube/config.yaml'
                // sh 'kubectl --kubeconfig=${kubeconfigPath} set image deployment/cs645-hw2 container-0=mythprat/swe645hw2:'+tag
                sh 'kubectl --kubeconfig=/home/ubuntu/.kube/config.yaml set image deployment/cs645-hw2 container-0=mythprat/swe645hw3:'+tag
            }
         }
      }
    }
}
