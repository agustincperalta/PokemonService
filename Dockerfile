FROM amazoncorretto:17


COPY target/PokemonService-*.jar /vol/component/jar/
RUN  find /vol/component/jar/ -iname PokemonService-\* -exec ln -sf '{}' /vol/component/jar/PokemonService.jar \;

HEALTHCHECK CMD curl -f http://localhost:8080/healthcheck || exit 1

CMD /vol/component/jar/run-service.sh
CMD ["java", "-jar", "/vol/component/jar/PokemonService.jar"]
