package com.example.study.entity

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface StockRepository: CrudRepository<Stock, String>{

}
