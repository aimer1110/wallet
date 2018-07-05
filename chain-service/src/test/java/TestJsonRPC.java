import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.longchain.entity.ResponseModel;
import com.longchain.service.TokenService;
import com.longchain.service.impl.TokenServiceImpl;
import com.longchain.utils.EthUtil;
import com.longchain.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.web3j.crypto.WalletFile;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.geth.Geth;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.CryptoPrimitive;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TestJsonRPC {
    public static void main(String[] args) {
//        Object[] params = new Object[]{};
//        String methodName = "personal_newAccount";
//        try {
//            JsonRpcHttpClient client = new JsonRpcHttpClient(new URL("http://127.0.0.1:8545"));
//            Object address = client.invoke(methodName, params, Object.class);
//            System.out.println(address);
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
        try {
//            Web3j web3 = Web3j.build(new HttpService());
//            EthBlock.Block block = web3.ethGetBlockByNumber(new DefaultBlockParameterNumber(1),true).send().getBlock();
//            System.out.println(StringUtils.md5Password("11`dsa-"));
//            List<String> list = parity.personalListAccounts().send().getAccountIds();
//            for (String s : list) {
//                System.out.println(s);
//            }
//            String from = "0xbcb1a8e2abb7ee6f90d010b3f0ade9572ebb8c12";
//            String to = "0x4a3a0e12708c3af517270372e5a79803d1cae219";
//            Transaction tx = new Transaction(from,EthUtil.getNonce(from),BigInteger.ZERO,BigInteger.valueOf(999999),to,BigInteger.ONE,null);
            Web3j web3j = EthUtil.initWeb3j();
            String acc1 = "0x1fe67f6652de5df4f3c7e07bbccdbf1c18c39cb5";
            String acc2 = "0x5f4da5f98a14bb1819ce98f213cbeff7a240d274";
            String contractAddress = "0x33059dcd63bee501ccbf42f6604296a5425b7b52";
//            System.out.println(EthUtil.unlockAccount(acc1,"111",BigInteger.valueOf(2)));
            String bagInfo = "hhh";
            String data = "0xbfd7b16c" + StringUtils.prefixTo32(acc1.substring(2)) + StringUtils.long2Hex(2l);
            Transaction tx = new Transaction(acc2, EthUtil.getNonce(acc2), BigInteger.ZERO, BigInteger.valueOf(9999999), contractAddress, BigInteger.ZERO, data);
            EthUtil.sendTransaction(tx, "111");
//            System.out.println(web3j.ethCall(tx, DefaultBlockParameterName.LATEST).send().getValue());
//            data = "0x67e0badb";
//            tx = new Transaction(acc1, EthUtil.getNonce(acc1), BigInteger.ZERO, BigInteger.valueOf(9999999), contractAddress, BigInteger.ZERO, data);
//            Long sugarBagNum = StringUtils.hex2Long(web3j.ethCall(tx, DefaultBlockParameterName.LATEST).send().getValue());
//            System.out.println("sugarBagNum:" + sugarBagNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
