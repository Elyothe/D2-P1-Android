import com.example.d2_p1.admin.data.repositoryImpl.SpaceRepositoryImpl
import com.example.d2_p1.admin.domain.repositories.SpaceRepository
import org.koin.dsl.module

val SpaceModule = module {
    single<SpaceRepository> { SpaceRepositoryImpl() }
}