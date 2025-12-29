# 1. Base image có Java 17
FROM maven:3.9.9-eclipse-temurin-17

# 2. Cài Chrome (để chạy Selenium headless)
RUN apt-get update && apt-get install -y \
    wget \
    gnupg \
    curl \
    unzip \
    && wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | apt-key add - \
    && echo "deb http://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google.list \
    && apt-get update \
    && apt-get install -y google-chrome-stable \
    && rm -rf /var/lib/apt/lists/*

# 3. Set working directory
WORKDIR /app

# 4. Copy project vào container
COPY . .

# 5. Command mặc định (có thể override)
CMD ["mvn", "clean", "test", "-Dsurefire.suiteXmlFiles=testNG.xml"]
