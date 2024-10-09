#!/bin/bash
#v.1.0

tags=( $@ )
len=${#tags[@]}

# Function to create .dockerignore file
createDockerIgnoreFile() {
  cat <<EOF > .dockerignore
.git
bin
src/main
src/test
EOF
}

# Function to process additional tags
processOtherTags() {
  local otherTags=("$@")
  for tag in "${otherTags[@]}"; do
    echo "Tagging image $image:$firstTag with $tag..."
    docker tag "$image:$firstTag" "$image:$tag"
  done
}

# Check if DockerImageName file exists and contains a valid image name
image=$(<DockerImageName)
if [ -z "$image" ]; then
  echo "Error: Cannot find an image name in the required file 'DockerImageName'."
  exit 1
fi

# Get tags from arguments, default to 'latest' if no tags provided
tags=("$@")
if [ ${#tags[@]} -eq 0 ]; then
  echo "No tags provided, defaulting to 'latest'."
  firstTag="latest"
else
  firstTag="${tags[0]}"
fi

# Create .dockerignore file
createDockerIgnoreFile

# Build the Docker image
echo "Building image $image:$firstTag..."
docker build -f Dockerfile -t "$image:$firstTag" .

# If more than one tag is provided, process additional tags
if [ ${#tags[@]} -gt 1 ]; then
  processOtherTags "${tags[@]:1}"
fi

# List the built image(s)
docker images "$image"