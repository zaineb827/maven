pipeline{
        agent any 

	tools{
	  maven 'mymaven'
           }
       stages{ 
         stage ('Checkout code')
	   {
             steps{
               git branch: 'main' , url: 'https://github.com/zaineb827/maven.git'
                 }
          }
          stage ('Build maven')
	   {
		steps{
		 sh 'mvn clean install'
		}
	     }
	  stage('Build Docker Image') {
            steps {
                sh 'docker build . -t my-country-service:$BUILD_NUMBER'
                withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
                    sh 'docker login -u zainebkallel -p $dockerhubpwd'
                }
                sh 'docker tag my-country-service:$BUILD_NUMBER zainebkallel/my-country-service:$BUILD_NUMBER'
                sh 'docker push zainebkallel/my-country-service:$BUILD_NUMBER'
            }
          }
         
         stage('Deploy to Kubernetes') {
          steps {
           script {
            withKubeConfig([credentialsId: 'kubeconfig-file', serverUrl: 'https://kubernetes.docker.internal:6443']) {
                sh 'kubectl apply -f deployment.yaml'
                sh 'kubectl apply -f service.yaml'
                }
              }
            }
         }    


   }
}
