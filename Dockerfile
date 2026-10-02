FROM tomcat:10.1-jdk21-temurin-jammy

# Create a clean ROOT folder structure first
RUN rm -rf /usr/local/tomcat/webapps/* && mkdir -p /usr/local/tomcat/webapps/ROOT

# Copy your local WebContent FILES straight into Tomcat's root directory
COPY WebContent/ /usr/local/tomcat/webapps/ROOT/

# Copy your Java source files into the place Tomcat looks for classes
COPY src/ /usr/local/tomcat/webapps/ROOT/WEB-INF/classes/

EXPOSE 8080
CMD ["catalina.sh", "run"]
