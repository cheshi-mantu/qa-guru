package qa.guru.allure;

import io.qameta.allure.Step;
import io.qameta.allure.okhttp3.AllureOkHttp3;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiSteps {
    private GitHubClient github;
    public ApiSteps(){
        String token = "";
        final fileReadHelper txtFile = new fileReadHelper();
        token = txtFile.getStringFromFile("D:\\code\\qa-guru\\allure-exercise\\github_oauth_token.secret");
        final String finalToken = token;
        System.out.println("Read token: " + finalToken);
        final OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor((chain) -> {
                    final Request request = chain.request().newBuilder()
                            .addHeader("Authorization", String.format("token %s", finalToken)).build();
                    System.out.println("Request's headers: " + request.headers());
                    return chain.proceed(request);
        })
                .addInterceptor(new AllureOkHttp3())
                .build();
        final Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.github.com")
                .client(client) // needed for integration with OkHttp3, i.e. we can listen the events
                .build();

        github = retrofit.create(GitHubClient.class);

    }
    @Step("Create issue with API")
    public Issue createIssue(final String owner,
                             final String repo,
                             final String title,
                             final String body) throws Exception {
        final Issue issue = new Issue();
        issue.setTitle(title);
        issue.setBody(body);
        return github.createIssue(owner, repo, issue).execute().body();

    }
}
