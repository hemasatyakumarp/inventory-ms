package com.hackerrank.inventory.service;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.hackerrank.inventory.model.Inventory;

@EnableBatchProcessing
@Configuration
public class CsvFileToDatabase {
	
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;
    
    // begin reader, writer, and processor

    
    @Bean
    public FlatFileItemReader<Inventory> csvAnimeReader(){
        FlatFileItemReader<Inventory> reader = new FlatFileItemReader<Inventory>();
        reader.setResource(new ClassPathResource("animescsv.csv"));
        reader.setLineMapper(new DefaultLineMapper<Inventory>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "id", "title", "description" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Inventory>() {{
                setTargetType(Inventory.class);
            }});
        }});
        return reader;
    }


	

	@Bean
	public JdbcBatchItemWriter<Inventory> csvAnimeWriter() {
		 JdbcBatchItemWriter<Inventory> csvAnimeWriter = new JdbcBatchItemWriter<Inventory>();
		 csvAnimeWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Inventory>());
		 csvAnimeWriter.setSql("INSERT INTO Inventory (id, productName, productLable,inventoryOnHand,minQtyReq,price) VALUES (:id, :productName, :productLable,:inventoryOnHand,:minQtyReq,:price)");
		 csvAnimeWriter.setDataSource(dataSource);
	        return csvAnimeWriter;
	}

	 // end reader, writer, and processor

    // begin job info
	@Bean
	public Step csvFileToDatabaseStep() {
		return stepBuilderFactory.get("csvFileToDatabaseStep")
				.<Inventory, Inventory>chunk(1)
				.reader(csvAnimeReader())
				//.processor(csvAnimeProcessor())
				.writer(csvAnimeWriter())
				.build();
	}

	@Bean
	Job csvFileToDatabaseJob(JobCompletionNotificationListener listener) {
		return jobBuilderFactory.get("csvFileToDatabaseJob")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.flow(csvFileToDatabaseStep())
				.end()
				.build();
	}
	 // end job info
}
