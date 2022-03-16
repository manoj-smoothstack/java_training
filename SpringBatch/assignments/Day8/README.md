# Assignment

Write a multi-threaded reader to process the items in Day2's transaction.csv dataset
and write them out to JSON using JsonFileItemWriter.

Here is an example JsonFileItemWriter:

```
@Bean
@StepScope
public JsonFileItemWriter<Book> jsonItemWriter(
  @Value("#{jobParameters['file.output']}") String output) throws IOException {
    JsonFileItemWriterBuilder<Book> builder = new JsonFileItemWriterBuilder<>();
    JacksonJsonObjectMarshaller<Book> marshaller = new JacksonJsonObjectMarshaller<>();
    return builder
      .name("myItemWriter")
      .jsonObjectMarshaller(marshaller)
      .resource(new FileSystemResource(output))
      .build();
}
```
