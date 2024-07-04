    package com.br.passagens.service;

    import org.springframework.stereotype.Service;
    import com.br.passagens.entity.Usuario;
    import com.br.passagens.repository.UsuarioRepository;

    import java.util.List;

    @Service
    public class UsuarioService {

        private UsuarioRepository usuarioRepository;

        public UsuarioService(UsuarioRepository usuarioRepository) {
            this.usuarioRepository = usuarioRepository;
        }

        public Usuario cadastrar(Usuario usuario) {
            return usuarioRepository.save(usuario);
        }

        public Usuario buscarUsuarioPorId(Long id) {
            return usuarioRepository.findById(id).orElse(null);
        }

        public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
            return usuarioRepository.findById(id).map(usuario -> {
                usuario.setNome(usuarioAtualizado.getNome());
                usuario.setEmail(usuarioAtualizado.getEmail());
                usuario.setNumero(usuarioAtualizado.getNumero());
                usuario.setSenha(usuarioAtualizado.getSenha());
                return usuarioRepository.save(usuario);
            }).orElse(null);
        }

        public void deletarUsuario(Long id) {
            usuarioRepository.deleteById(id);
        }

        public List<Usuario> buscarTodosUsuarios() {
            return usuarioRepository.findAll();
        }
    }
