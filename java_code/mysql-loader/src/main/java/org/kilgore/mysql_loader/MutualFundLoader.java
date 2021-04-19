package org.kilgore.mysql_loader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class MutualFundLoader {
	
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        String fileName = "MFs_a-z.csv";

        CsvMapper mapper = new CsvMapper();
        mapper.disable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);

        CsvSchema schema = mapper.schemaFor(MutualFund.class).withHeader();

        System.out.println(schema);
        String content = Files.readString(Paths.get(fileName).toAbsolutePath());
        MappingIterator<MutualFund> it = mapper.readerFor(MutualFund.class).with(schema).readValues(content);

        List<MutualFund> mfers = it.readAll();

        
    	Class.forName("com.mysql.cj.jdbc.Driver"); 
    	Connection con=DriverManager.getConnection("jdbc:mysql://ilc-avtns-vsl02:3306/redispoc","mf_user","c@tsMe0w");
        
        
        String sql = "INSERT INTO redispoc.mutual_funds " +
                "(       fund_symbol, fund_extended_name, fund_family, inception_date, category, rating, return_rating, risk_rating, investment_strategy, investment_type, " +
                 "       size_type, " +
                 "       currency, " +
                 "       fund_net_annual_expense_ratio, " +
                 "       category_net_annual_expense_ratio, " +
                 "       asset_cash, " +
                 "       asset_stocks, " +
                 "       asset_bonds, " +
                 "       asset_others, " +
                 "       asset_preferred, " +
                 "       asset_convertable) " +
                 "       VALUES " +
                 "       (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); ";
                 
        System.out.println(sql);
        con.setAutoCommit(false);
        
        PreparedStatement ps = con.prepareStatement(sql);
        
        AtomicInteger index = new AtomicInteger(0);
        mfers.stream().forEach(mf -> {
        	try {
				ps.setString(1, mf.getFundSymbol());
				ps.setString(2, mf.getFundExtendedName());
				ps.setString(3, mf.getFundFamily());
				ps.setDate(4, new java.sql.Date(mf.getInceptionDate().getTime()));
				ps.setString(5, mf.getCategory());
				if(mf.getRating() == null) {
					ps.setNull(6,  java.sql.Types.INTEGER);	
				}else {
					ps.setInt(6, mf.getRating());
				}
				if(mf.getReturnRating() == null) {
					ps.setNull(7,  java.sql.Types.INTEGER);	
				}else {
					ps.setInt(7, mf.getReturnRating());
				}
				if(mf.getRiskRating() == null) {
					ps.setNull(8,  java.sql.Types.INTEGER);	
				}else {
					ps.setInt(8, mf.getRiskRating());
				}

				ps.setString(9, mf.getInvestmentStrategy());
				ps.setString(10, mf.getInvestmentType());
				ps.setString(11, mf.getSizeType());
				ps.setString(12, mf.getCurrency());
				ps.setFloat(13, mf.getFundNetAnnualExpenseRation());
				ps.setFloat(14, mf.getCategoryNetAnnualExpenseRatio());
				ps.setFloat(15,mf.getAssetCash());
				ps.setFloat(16, mf.getAssetStocks());
				ps.setFloat(17, mf.getAssetBonds());
				ps.setFloat(18, mf.getAssetOthers());
				ps.setFloat(19, mf.getAssetPreferred());
				ps.setFloat(20, mf.getAssetConvertable());
				
				ps.executeUpdate();
				con.commit();
				System.out.println("inserted "+index.getAndIncrement());
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
        
        con.close();
        System.out.println("Finished");
        
        
////        AtomicInteger count = new AtomicInteger(0);
//        mfers.stream().map(MutualFund::getFundFamily)
//                .collect(Collectors.toSet())
//                .stream()
//                .collect(Collectors.toList())
//                .stream().sorted()
//                .forEach(System.out::println);
//
//
//        mfers.stream().filter(mutualFund -> "Ancora".equals(mutualFund.getFundFamily())).forEach(System.out::println);
    }	

}
