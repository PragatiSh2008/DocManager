FROM tomcat:10.1-jdk21-temurin-jammy

# 1. Create a clean ROOT folder structure
RUN rm -rf /usr/local/tomcat/webapps/* && mkdir -p /usr/local/tomcat/webapps/ROOT

# 2. Copy your frontend files
COPY WebContent/ /usr/local/tomcat/webapps/ROOT/

# 3. Copy your Java source files into the container
COPY src/ /usr/local/tomcat/webapps/ROOT/WEB-INF/classes/

# 4. Compile all the Java servlet files inside the container
RUN javac -cp "/usr/local/tomcat/lib/*:/usr/local/tomcat/webapps/ROOT/WEB-INF/lib/*" /usr/local/tomcat/webapps/ROOT/WEB-INF/classes/OmniDocs/*.java

EXPOSE 8080
CMD ["catalina.sh", "run"]
